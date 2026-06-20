package com.agrim.notesapi.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            NoteNotFoundException.class
    )
    public ResponseEntity<String>
    handleNote(
            NoteNotFoundException ex
    ) {
        return ResponseEntity
                .status(404)
                .body(
                        ex.getMessage()
                );
    }
    @ExceptionHandler(
            RuntimeException.class
    )
    public ResponseEntity<String>
    handleRuntime(
            RuntimeException ex
    ) {
        return ResponseEntity
                .badRequest()
                .body(
                        ex.getMessage()
                );
    }

}