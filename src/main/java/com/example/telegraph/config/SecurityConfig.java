package com.example.telegraph.config;

import com.example.telegraph.filter.JWTFilter;
import com.example.telegraph.service.AuthService;
import com.example.telegraph.service.AuthenticationService;
import com.example.telegraph.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthService authService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((authorize)-> {
                    authorize
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/post/delete/{id}").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JWTFilter(authenticationService,jwtService),
        UsernamePasswordAuthenticationFilter.class).build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
