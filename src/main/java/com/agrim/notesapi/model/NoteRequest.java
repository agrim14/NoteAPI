package com.agrim.notesapi.model;

import jakarta.validation.constraints.NotBlank;

public class NoteRequest {

    @NotBlank(message = "Text cannot be empty")
    private String text;

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }
}