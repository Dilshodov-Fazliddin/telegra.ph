package com.example.telegraph.service;

import com.example.telegraph.config.SecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.Converters;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    public void authentication(Claims claims, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        List<String>roles= (List<String>) claims.get("roles");
        String username = claims.getSubject();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,null,getRoles(roles)
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public List<SimpleGrantedAuthority>getRoles(List<String>roles) throws JsonProcessingException{
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }
}
