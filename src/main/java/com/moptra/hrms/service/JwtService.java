package com.moptra.hrms.service;

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

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())); // Include roles in token

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims) //  This was missing!
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(key, Jwts.SIG.HS256)
                .compact();
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
