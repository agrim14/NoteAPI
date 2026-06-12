package com.agrim.notesapi.service;


import com.agrim.notesapi.dto.RegisterRequest;
import com.agrim.notesapi.model.User;
import com.agrim.notesapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }



    public String register(RegisterRequest request){


        User user = new User();


        user.setUsername(request.getUsername());


        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        user.setRole("USER");


        userRepository.save(user);


        return "User registered successfully";

    }

}