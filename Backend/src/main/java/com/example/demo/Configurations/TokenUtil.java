package com.example.demo.Configurations;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenUtil {
  
    private String secrte = "lkanqiwfh98wgufbqdq8g3wfbviwh408iveriver23vwwiugf98g";  
    private String secret = "lkanqiwfh98wgufbqdq8g3wfbviwh408iveriver23vwwiugf98g";



    ///////////////////
    
    public String generateTokenByEmail(String email) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    
    private Date generateExpirationDate() {
        return java.sql.Date.valueOf(LocalDate.now().plusWeeks(4));
    }
    ///////////////////


    public String genrateToken(UserDetails userDetails){
        
        Map<String,Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        
        return Jwts
                    .builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, secrte)
                    .compact();
                    
    }



    public String getUserNameFromString(String token){
            Claims cliams = Jwts
                                .parser()
                                .setSigningKey(secrte)
                                .parseClaimsJws(token)
                                .getBody();

            System.out.println("+++++++++++++++++++++++++++++++++++");
            System.out.println(cliams.getSubject());
            System.out.println("+++++++++++++++++++++++++++++++++++");

            return cliams.getSubject();
        
    }

    public boolean isValied(String token, UserDetails userDetails) {
        String username = getUserNameFromString(token);
        return (username.equals( userDetails.getUsername())&&!isTokenExpaired(token));
    }

    private boolean isTokenExpaired( String token) {

        Date ex = getClaims(token).getExpiration();
        return ex.before(new Date());
        
    }

    private Claims getClaims(String token){

        Claims claims ;

        try {
            claims = Jwts.parser().setSigningKey(secrte).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null ;
        }

        return claims;

    }




    //////////////////////
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(secrte)
                   .parseClaimsJws(token)
                   .getBody();
    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        System.out.println("\n--------------\n");
        System.out.println(claims.getSubject());
        System.out.println("\n--------------\n");
        return claimsResolver.apply(claims);
    }

}
