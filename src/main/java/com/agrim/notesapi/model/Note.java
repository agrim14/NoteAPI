package com.agrim.notesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Note {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private int id;
    private String text;
    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;
    @Column(
            nullable = false
    )
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonIgnoreProperties({
            "password",
            "notes"
    })
    private User user;
    public Note() {
    }
    public Note(
            String text,
            User user
    ) {
        this.text =
                text;
        this.user =
                user;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setText(
            String text
    ) {
        this.text =
                text;
    }
    public void setUser(
            User user
    ) {
        this.user =
                user;
    }
    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt =
                createdAt;
    }
    public void setUpdatedAt(
            LocalDateTime updatedAt
    ) {
        this.updatedAt =
                updatedAt;
    }
    @PrePersist
    public void prePersist() {
        createdAt =
                LocalDateTime.now();
        updatedAt =
                LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt =
                LocalDateTime.now();
    }

}