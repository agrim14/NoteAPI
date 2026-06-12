package com.agrim.notesapi.controller;


import com.agrim.notesapi.dto.RegisterRequest;
import com.agrim.notesapi.service.UserService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {


    private final UserService userService;


    public AuthController(UserService userService){

        this.userService = userService;

    }



    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ){

        return userService.register(request);

    }

}