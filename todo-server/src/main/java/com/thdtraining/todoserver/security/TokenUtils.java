package com.thdtraining.todoserver.security;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/* 6. Create a util class to generate token */
public class TokenUtils {
    
    /* 7. Create access secret token and time of token validation */
    //@Value("${access.token.secret}")
    private static final String ACCESS_TOKEN_SECRET = "MyAccessDataToken1234skskdhjdlfjeinfjdkd";
    @Value("${access.token.valid-time}")
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 3_600L;

    /* 8. Create a function to generate token using access token   */
    public static String createToken(String name, String email, Collection<? extends GrantedAuthority> collection){

        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000L;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String,Object> extraInfo = new HashMap<>();
        extraInfo.put("name", name);
        extraInfo.put("email",email);
        extraInfo.put("roles",collection);

        return Jwts.builder()
            .setSubject(email)
            .setExpiration(expirationDate)
            .addClaims(extraInfo)
            .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
            .compact();
    }

    /* 9. Create a method that return a UsernamePasswordAuthenticationToken that spring security identify as valid  */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    
            String email = claims.getSubject();
            String rolesString = claims.get("roles").toString().replace("authority=", "").replaceAll("\\W", "");

            List<GrantedAuthority> rolesList = new ArrayList<>();

            rolesList.add(new SimpleGrantedAuthority(rolesString));
    
            return new UsernamePasswordAuthenticationToken(email, null, rolesList);

        } catch(JwtException e){
            return null;
        }
    }

}
