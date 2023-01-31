package com.thdtraining.todoserver.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/* 13. Create a class for user Authentication and override two methods */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /* 14. This methods takes parameters from the request and mapped to authCredentials then return it as UsernamePasswordAuthentication instance */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();

        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch(IOException e){

        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(authCredentials.getEmail(),authCredentials.getPassword(),Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    /* 15. This method create an instance of UserDetailsImp and set its value from authResult, then creates a token and create a response with that token */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserDetailsImp userDetails = (UserDetailsImp) authResult.getPrincipal();

        String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername(), userDetails.getAuthorities());

        response.addHeader("Authorization", "Bearer "+token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
