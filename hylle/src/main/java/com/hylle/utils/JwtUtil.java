package com.hylle.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    private static final long EXPIRATION_TIME_MS = 10 * 60 * 60 * 1000; // 10 horas

    @PostConstruct
    public void init() {
        System.out.println("JWT Secret from properties: " + secret);
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("jwt.secret property is missing or empty");
        }
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
            System.out.println("JwtUtil secretKey initialized, length: " + keyBytes.length + " bytes");
            System.out.println("This is your secret: "+ secret);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to decode jwt.secret from Base64", e);
        }
    }

    // Gera um token com o username como subject
    public String generateToken(String username) {
        if (this.secretKey == null) {
            throw new IllegalStateException("SecretKey not initialized");
        }
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrai o username do token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Valida o token comparando username e expirado
    public boolean validateToken(String token, String username) {
        try {
            return extractUsername(token).equals(username) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token inválido ou mal formado
        }
    }

    // Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Extrai qualquer claim genérica usando função
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claimsResolver.apply(claims);
        } catch (JwtException e) {
            throw e;
        }
    }
}
