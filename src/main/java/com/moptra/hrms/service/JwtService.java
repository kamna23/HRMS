package com.moptra.hrms.service;

import com.moptra.hrms.model.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret"; // Must be at least 32 chars
    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public AuthResponse generateToken(UserDetails userDetails) {
        long expirationMillis = 1000 * 60 * 30; // 30 minutes
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + expirationMillis);

        Map<String, List<String>> claims = new HashMap<>();
         List<String> roles =userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()); // Include roles in token
        claims.put("roles",roles);

        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims) //  This was missing!
                .issuedAt(issuedAt)
                .expiration(expiresAt) // 30 min
                .signWith(key, Jwts.SIG.HS256)
                .compact();

        return new AuthResponse(token,"Bearer",expiresAt,userDetails.getUsername(),roles);
    }

    public String extractEmail(String token) {
        JwtParser parser = Jwts.parser().verifyWith(key).build();
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(key).build();
            Date expiration = parser.parseSignedClaims(token).getPayload().getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // If parsing fails, treat it as expired
        }
    }

    // Optional: Extract roles if you want to debug them
    public Object extractRoles(String token) {
        JwtParser parser = Jwts.parser().verifyWith(key).build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.get("roles");
    }
}
