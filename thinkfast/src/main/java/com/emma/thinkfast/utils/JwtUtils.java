package com.emma.thinkfast.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    //See about moving (and changing) this to the .properties file
    private static final String JWT_SECRET = "InThisHome-WeAreInIt";
    private static final long JWT_EXPIRATION_MS = 86400000; //1 day

    private JwtUtils() {}

    public static String generateToken(String userName) {
        return Jwts.builder()
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact();
    }

    public static String getUserNameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            //log validation error
        }
        return false;
    }
}
