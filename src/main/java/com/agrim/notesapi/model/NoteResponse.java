package com.agrim.notesapi.model;

public class NoteResponse {

    private int id;
    private String text;

    public NoteResponse(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}