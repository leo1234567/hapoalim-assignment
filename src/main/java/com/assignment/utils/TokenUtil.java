package com.assignment.utils;

import java.util.Date;
import java.util.HashMap;

import com.assignment.properties.AssignmentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

    public static final long JWT_TOKEN_EXPIRATION = 1000 * 60 * 60 * 10;

    @Autowired
    private AssignmentProperties assignmentProperties;

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(assignmentProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION)).signWith(SignatureAlgorithm.HS512, assignmentProperties.getSecret()).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getClaims(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
