package com.agrim.notesapi.controller;

import com.agrim.notesapi.model.*;
import com.agrim.notesapi.service.AiService;
import com.agrim.notesapi.service.NoteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    private final NoteService noteService;
    private final AiService aiService;
    public HelloController(
            NoteService noteService,
            AiService aiService
    ) {
        this.noteService = noteService;
        this.aiService = aiService;
    }
    @GetMapping("/")
    public String home() {
        return "Hello "
                +
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
                +
                "!";
    }
    @PostMapping("/message")
    public String receiveMessage(
            @RequestBody
            MessageRequest request
    ) {
        return
                "Message received: "
                        +
                        request.getMessage();
    }
    @GetMapping("/hello/{name}")
    public String greetUser(
            @PathVariable
            String name
    ) {
        return
                "Hello "
                        +
                        name;
    }
    @PostMapping("/notes")
    public ResponseEntity<String>
    addNote(
            @Valid
            @RequestBody
            NoteRequest request
    ) {
        return ResponseEntity
                .status(
                        HttpStatus.CREATED
                )
                .body(
                        noteService
                                .addNote(
                                        request.getText()
                                )
                );
    }
    @GetMapping("/notes")
    public ResponseEntity<
            ApiResponse<
                    List<NoteResponse>
                    >
            >
    getNotes() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notes fetched",
                        noteService
                                .getNoteResponses()
                )
        );
    }
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<String>
    deleteNote(
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(
                noteService
                        .deleteNote(id)
        );
    }
    @PutMapping("/notes/{id}")
    public ResponseEntity<String>
    updateNote(
            @PathVariable
            int id,
            @RequestBody
            NoteRequest request
    ) {
        return ResponseEntity.ok(
                noteService
                        .updateNote(
                                id,
                                request.getText()
                        )
        );
    }
    @GetMapping("/notes/search")
    public ResponseEntity<
            List<Note>
            >
    searchNotes(
            @RequestParam
            String text
    ) {
        return ResponseEntity.ok(
                noteService
                        .searchNotes(text)
        );
    }
    @GetMapping("/notes-dto")
    public ResponseEntity<
            List<NoteResponse>
            >
    getNotesDto() {
        return ResponseEntity.ok(
                noteService
                        .getNoteResponses()
        );
    }
    @GetMapping("/notes-page")
    public ResponseEntity<
            List<Note>
            >
    getNotesPage(
            @RequestParam
            int page,
            @RequestParam
            int size
    ) {
        return ResponseEntity.ok(
                noteService
                        .getNotesPage(
                                page,
                                size
                        )
        );
    }
    @GetMapping("/notes-sorted")
    public ResponseEntity<
            List<Note>
            >
    getNotesSorted() {
        return ResponseEntity.ok(
                noteService
                        .getNotesSorted()
        );
    }
    @GetMapping("/notes-exact")
    public ResponseEntity<
            List<Note>
            >
    getNotesExact(
            @RequestParam
            String text
    ) {
        return ResponseEntity.ok(
                noteService
                        .getNotesExact(text)
        );
    }
    @PostMapping("/ai/summarize")
    public ResponseEntity<
            ApiResponse<AiResponse>
            >
    summarize(
            @RequestBody
            AiRequest request
    ) {
        String summary =
                aiService
                        .summarize(
                                request.getText()
                        );
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Summary generated",
                        new AiResponse(
                                summary
                        )
                )
        );
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "Welcome Admin!";
    }

}