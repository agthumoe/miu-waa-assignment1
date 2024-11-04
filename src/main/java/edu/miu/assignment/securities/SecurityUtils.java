package edu.miu.assignment.securities;

import edu.miu.assignment.exceptions.HttpStatusException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public final class SecurityUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
    private static final long expirataion = 5 * 60 * 60 * 60;
    private static final String secret = "top-secret";

    /**
     * Just to prevent instantiation.
     */
    private SecurityUtils() {
    }

    /**
     * Will implements and get principle name from spring security context in the future.
     *
     * @return Authenticated user's name.
     */
    public static String getPrinciple() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirataion))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirataion * 60))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new HttpStatusException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
