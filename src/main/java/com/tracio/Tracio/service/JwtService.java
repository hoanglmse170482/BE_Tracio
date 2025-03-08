package com.tracio.Tracio.service;

import com.tracio.Tracio.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "JWT-SERVICE")
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expired-time-access-token}")
    private Long expiredAccessToken;

    @Value("${jwt.expired-time-refresh-token}")
    private Long expiredRefreshToken;


    public String generateAccessToken(User user) {
        log.info("Generate access token");

        return Jwts.builder()
                .claim(Claims.SUBJECT, user.getEmail())
                .claim(Claims.ISSUED_AT, new Date())
                .claim(Claims.EXPIRATION, new Date(Instant.now().plus(expiredAccessToken, ChronoUnit.HOURS).toEpochMilli()))
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .claim(Claims.SUBJECT, user.getEmail())
                .claim(Claims.ISSUED_AT, new Date())
                .claim(Claims.EXPIRATION, new Date(Instant.now().plus(expiredRefreshToken, ChronoUnit.DAYS).toEpochMilli()))
                .signWith(getKey())
                .compact();
    }

    public Claims verifyAccessToken(String token) {
        var claim = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        if(claim.getExpiration().before(new Date())) {
            throw new RuntimeException("Expired access token");
        }
        return claim;
    }

    public String extractUsername(String token) {
        return Jwts.parser().build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
}
