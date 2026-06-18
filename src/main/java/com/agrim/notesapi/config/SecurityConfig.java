package com.agrim.notesapi.config;

import com.agrim.notesapi.jwt.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication
        .UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;


    public SecurityConfig(
            JwtFilter jwtFilter
    ) {

        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(
                        csrf ->
                                csrf.disable()
                )

                .authorizeHttpRequests(

                        auth ->

                                auth

                                        .requestMatchers(

                                                "/",

                                                "/swagger-ui/**",

                                                "/v3/api-docs/**",

                                                "/auth/register",

                                                "/auth/login"

                                        )

                                        .permitAll()

                                        .requestMatchers(

                                                "/admin"

                                        )

                                        .hasRole("ADMIN")

                                        .anyRequest()

                                        .authenticated()
                )

                .httpBasic(

                        basic ->

                                basic.disable()
                )

                .formLogin(

                        login ->

                                login.disable()
                )

                .addFilterBefore(

                        jwtFilter,

                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}