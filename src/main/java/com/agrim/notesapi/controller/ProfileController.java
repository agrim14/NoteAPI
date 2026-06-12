package com.agrim.notesapi.controller;

import com.agrim.notesapi.dto.ProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @GetMapping
    public ProfileResponse profile(Authentication authentication){

        String username = authentication.getName();

        String role = authentication
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();


        return new ProfileResponse(username, role);
    }
}