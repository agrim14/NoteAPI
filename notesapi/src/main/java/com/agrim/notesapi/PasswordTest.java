package com.agrim.notesapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordTest {

    public static void main(String[] args) {

        PasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println(
                encoder.encode("agrim_dev")
        );
    }
}