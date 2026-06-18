package com.agrim.notesapi.controller;


import com.agrim.notesapi.dto.RegisterRequest;
import com.agrim.notesapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import com.agrim.notesapi.dto.LoginRequest;
import com.agrim.notesapi.jwt.JwtService;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication
        .UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserService userService;


    public AuthController(

            UserService userService,

            AuthenticationManager authenticationManager,

            JwtService jwtService
    ) {

        this.userService = userService;

        this.authenticationManager =
                authenticationManager;

        this.jwtService =
                jwtService;
    }

    @PostMapping("/login")
    public String login(

            @RequestBody
            LoginRequest request
    ) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getUsername(),

                        request.getPassword()
                )
        );

        return jwtService.generateToken(

                request.getUsername()
        );
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ){

        return userService.register(request);

    }

}