package com.agrim.notesapi.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String text;


    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"password","notes"})
    private User user;


    public Note() {

    }


    public Note(String text, User user) {

        this.text = text;
        this.user = user;
    }


    public int getId() {

        return id;
    }


    public String getText() {

        return text;
    }


    public User getUser() {

        return user;
    }


    public void setText(String text) {

        this.text = text;
    }


    public void setUser(User user) {

        this.user = user;
    }
}