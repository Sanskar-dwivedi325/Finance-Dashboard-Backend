package com.sanskar.finance_dashboard.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String email,String role) {
        return Jwts.builder().setSubject(email).claim("role",role).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key).compact();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try{
            getClaims(token);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

}
