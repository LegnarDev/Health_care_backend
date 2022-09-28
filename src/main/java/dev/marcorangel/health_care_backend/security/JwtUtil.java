package dev.marcorangel.health_care_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    private int jwtExpirationMs;

    public String encode(String sub) {

        if (sub == null || sub.equals("")) {
            return null;
        }
        Instant exp = Instant.now();
        return Jwts.builder()
                .setSubject(sub)
                .setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(new Date(exp.toEpochMilli() + jwtExpirationMs * 1000L))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String resolveToken(ServletRequest request) {
        String authHeader = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Token ")) {
            return null;
        }
        return authHeader.substring("Token ".length());
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException e) {
            return false;
        }
    }

    public String getSub(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}