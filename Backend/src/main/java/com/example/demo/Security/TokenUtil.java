package com.example.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenUtil {

    private String secret = "lkanqiwfh98wgufbqdq8g3wfbviwh408iveriver23vwwiugf98g";

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(4)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean isValid(String token, UserDetails userDetails) {
        String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !getClaimFromToken(token, Claims::getExpiration).before(new Date()));
    }

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
