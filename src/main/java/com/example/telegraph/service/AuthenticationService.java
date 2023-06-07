package com.example.telegraph.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    public void authentication(Claims claims, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        List<String>roles= (List<String>) claims.get("userRoles");
        String username = claims.getSubject();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                getRoles(roles)
        );
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

        SecurityContextHolder
                .getContext().
                setAuthentication(authenticationToken);
    }

    public List<SimpleGrantedAuthority>getRoles(List<String>roles) throws JsonProcessingException{
        return roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
