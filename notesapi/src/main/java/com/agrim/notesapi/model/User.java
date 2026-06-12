package com.agrim.notesapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();


    public User() {

    }


    public User(String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }


    public int getId() {

        return id;
    }


    public String getUsername() {

        return username;
    }


    public String getPassword() {

        return password;
    }


    public String getRole() {

        return role;
    }


    public List<Note> getNotes() {

        return notes;
    }


    public void setUsername(String username) {

        this.username = username;
    }


    public void setPassword(String password) {

        this.password = password;
    }


    public void setRole(String role) {

        this.role = role;
    }
}