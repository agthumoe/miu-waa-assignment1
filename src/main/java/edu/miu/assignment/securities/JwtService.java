package edu.miu.assignment.securities;

import edu.miu.assignment.exceptions.HttpStatusException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);
    @Value("${app.security.jwt.secret}")
    private String secret;
    @Value("${app.security.jwt.expiration}")
    private long accessTokenExpirationTime;
    @Value("${app.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpirationTime;

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new HttpStatusException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }
}
