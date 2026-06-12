package com.agrim.notesapi.controller;
import com.agrim.notesapi.model.NoteResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.agrim.notesapi.model.MessageRequest;
import com.agrim.notesapi.model.Note;
import com.agrim.notesapi.model.NoteRequest;
import com.agrim.notesapi.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class HelloController {

    private final NoteService noteService;


    // Constructor Injection
    public HelloController(NoteService noteService) {

        this.noteService = noteService;
    }


    @GetMapping("/")
    public String home() {

        return "Hello "
                + SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                + "!";
    }


    @PostMapping("/message")
    public String receiveMessage(@RequestBody MessageRequest request) {

        return "Message received: " + request.getMessage();
    }


    @GetMapping("/hello/{name}")
    public String greetUser(@PathVariable String name) {

        return "Hello " + name;
    }


    @PostMapping("/notes")
    public ResponseEntity<String> addNote(
            @Valid @RequestBody NoteRequest request) {

        String response = noteService.addNote(request.getText());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {

        return ResponseEntity.ok(noteService.getNotes());
    }


    @DeleteMapping("/notes/{id}")
    public ResponseEntity<String> deleteNote(
            @PathVariable int id) {

        String response = noteService.deleteNote(id);

        if (response.equals("Note deleted successfully")) {

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<String> updateNote(
            @PathVariable int id,
            @RequestBody NoteRequest request) {

        String response =
                noteService.updateNote(id, request.getText());

        if (response.equals("Note updated successfully")) {

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @GetMapping("/notes/search")
    public ResponseEntity<List<Note>> searchNotes(
            @RequestParam String text) {

        return ResponseEntity.ok(
                noteService.searchNotes(text)
        );
    }
    @GetMapping("/notes-dto")
    public ResponseEntity<List<NoteResponse>> getNotesDto() {

        return ResponseEntity.ok(
                noteService.getNoteResponses()
        );
    }
    @GetMapping("/notes-page")
    public ResponseEntity<List<Note>> getNotesPage(

            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(
                noteService.getNotesPage(page, size)
        );
    }
    @GetMapping("/notes-sorted")
    public ResponseEntity<List<Note>> getNotesSorted() {

        return ResponseEntity.ok(
                noteService.getNotesSorted()
        );
    }
    @GetMapping("/notes-exact")
    public ResponseEntity<List<Note>> getNotesExact(
            @RequestParam String text) {

        return ResponseEntity.ok(
                noteService.getNotesExact(text)
        );
    }
    @GetMapping("/admin")
    public String adminPage() {

        return "Welcome Admin!";
    }
}