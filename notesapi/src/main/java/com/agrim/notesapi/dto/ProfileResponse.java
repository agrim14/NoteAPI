package com.agrim.notesapi.dto;

public class ProfileResponse {

    private String username;
    private String role;


    public ProfileResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }


    public String getRole() {
        return role;
    }
}