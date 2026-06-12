package com.agrim.notesapi.service;

import com.agrim.notesapi.model.Note;
import com.agrim.notesapi.model.NoteResponse;
import com.agrim.notesapi.model.User;
import com.agrim.notesapi.repository.NoteRepository;
import com.agrim.notesapi.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;


    public NoteService(
            NoteRepository noteRepository,
            UserRepository userRepository
    ) {

        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    public String addNote(String text) {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow();

        Note note = new Note();

        note.setText(text);

        note.setUser(user);

        noteRepository.save(note);

        return "Note added successfully";
    }


    public List<Note> getNotes() {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow();

        return noteRepository.findByUser(user);
    }


    public String deleteNote(int id) {

        if (noteRepository.existsById(id)) {

            noteRepository.deleteById(id);

            return "Note deleted successfully";
        }

        return "Note not found";
    }


    public String updateNote(int id, String text) {

        Optional<Note> optionalNote =
                noteRepository.findById(id);

        if (optionalNote.isPresent()) {

            Note note = optionalNote.get();

            note.setText(text);

            noteRepository.save(note);

            return "Note updated successfully";
        }

        return "Note not found";
    }


    public List<Note> searchNotes(String text) {

        return noteRepository.findByTextContaining(text);
    }


    public List<NoteResponse> getNoteResponses() {

        return noteRepository
                .findAll()
                .stream()
                .map(note ->
                        new NoteResponse(
                                note.getId(),
                                note.getText()
                        ))
                .toList();
    }


    public List<Note> getNotesPage(
            int page,
            int size
    ) {

        return noteRepository
                .findAll(
                        PageRequest.of(
                                page,
                                size
                        )
                )
                .getContent();
    }


    public List<Note> getNotesSorted() {

        return noteRepository.findAll(
                Sort.by("text")
        );
    }


    public List<Note> getNotesExact(String text) {

        return noteRepository
                .getNotesByExactText(text);
    }
}