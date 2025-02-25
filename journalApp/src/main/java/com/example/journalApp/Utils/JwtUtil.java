package com.example.journalApp.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String secretKey = "" ;
    public JwtUtil()  {
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256") ;
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }
       catch (Exception e ){
            throw new RuntimeException(e);
       }
    }

    public String generateToken(String userName) {

        Map<String , Object> claims = new HashMap<>() ;

        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .header().empty().add("typ" , "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5 )) // 5 means ==> 5 Minute Expiration is set on the token
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes) ;

    }

    public boolean validateToken(String token ){
        return isTokenExpired(token) ;

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()) ;
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith( getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload() ;
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
}
