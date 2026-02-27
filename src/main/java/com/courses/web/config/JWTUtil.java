package com.courses.web.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)) 
                .signWith(getSignedKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignedKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Claims isValid(String jwt){

        try{
           return Jwts.parserBuilder()
                    .setSigningKey(getSignedKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch(Exception e){
             throw new InvalidTokenException("Invalid token");
        }
    }

    public String getUsernameFromToken(String token){
        Claims claims = isValid(token);
        if(claims != null){
            return claims.getSubject();
        }
        throw new InvalidTokenException("Invalid token");
    }

    public boolean isTokenExpired(String token){
        Claims claims = isValid(token);
        if(claims != null){
            return claims.getExpiration().before(new Date());
        }
        throw new InvalidTokenException("Invalid token");
    }

    
}
