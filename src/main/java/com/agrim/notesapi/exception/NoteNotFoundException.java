package com.agrim.notesapi.exception;

public class NoteNotFoundException
        extends RuntimeException {

    public NoteNotFoundException(String message) {

        super(message);
    }
}