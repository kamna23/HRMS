package com.moptra.hrms.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret"; // min 32 chars
    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
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
            parser.parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
