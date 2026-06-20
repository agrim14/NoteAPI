package com.agrim.notesapi.service;

import com.agrim.notesapi.exception.NoteNotFoundException;

import com.agrim.notesapi.model.Note;
import com.agrim.notesapi.model.NoteResponse;
import com.agrim.notesapi.model.User;

import com.agrim.notesapi.repository.NoteRepository;
import com.agrim.notesapi.repository.UserRepository;

import org.springframework.stereotype.Service;

import org.springframework.security.core.context
        .SecurityContextHolder;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;


    public NoteService(

            NoteRepository noteRepository,

            UserRepository userRepository

    ) {

        this.noteRepository =
                noteRepository;

        this.userRepository =
                userRepository;
    }


    public String addNote(
            String text
    ) {

        User user =
                getLoggedInUser();

        Note note =
                new Note();

        note.setText(
                text
        );

        note.setUser(
                user
        );

        noteRepository.save(
                note
        );

        return
                "Note added successfully";
    }


    public List<Note> getNotes() {

        User user =
                getLoggedInUser();

        return noteRepository
                .findByUser(
                        user
                );
    }


    public String deleteNote(
            int id
    ) {

        User user =
                getLoggedInUser();

        Note note =

                noteRepository

                        .findById(id)

                        .orElseThrow(

                                () ->

                                        new NoteNotFoundException(

                                                "Note not found"
                                        )
                        );

        if (

                note.getUser()

                        .getId()

                        !=

                        user.getId()

        ) {

            throw new RuntimeException(
                    "Access denied"
            );
        }

        noteRepository.delete(
                note
        );

        return
                "Note deleted successfully";
    }


    public String updateNote(

            int id,

            String text

    ) {

        User user =
                getLoggedInUser();

        Note note =

                noteRepository

                        .findById(id)

                        .orElseThrow(

                                () ->

                                        new NoteNotFoundException(

                                                "Note not found"
                                        )
                        );

        if (

                note.getUser()

                        .getId()

                        !=

                        user.getId()

        ) {

            throw new RuntimeException(
                    "Access denied"
            );
        }

        note.setText(
                text
        );

        noteRepository.save(
                note
        );

        return
                "Note updated successfully";
    }


    public List<Note> searchNotes(
            String text
    ) {

        User user =
                getLoggedInUser();

        return noteRepository

                .findByUser(
                        user
                )

                .stream()

                .filter(

                        note ->

                                note.getText()

                                        .toLowerCase()

                                        .contains(

                                                text
                                                        .toLowerCase()
                                        )
                )

                .toList();
    }


    public List<NoteResponse>
    getNoteResponses() {

        return getNotes()

                .stream()

                .map(

                        note ->

                                new NoteResponse(

                                        note.getId(),

                                        note.getText(),

                                        note.getCreatedAt(),

                                        note.getUpdatedAt()
                                )
                )

                .toList();
    }


    public List<Note>
    getNotesPage(

            int page,

            int size

    ) {

        return getNotes()

                .stream()

                .skip(
                        (long) page * size
                )

                .limit(
                        size
                )

                .toList();
    }


    public List<Note>
    getNotesSorted() {

        return getNotes()

                .stream()

                .sorted(

                        (a, b) ->

                                a.getText()

                                        .compareTo(

                                                b.getText()
                                        )
                )

                .toList();
    }


    public List<Note>
    getNotesExact(
            String text
    ) {

        return getNotes()

                .stream()

                .filter(

                        note ->

                                note.getText()

                                        .equals(
                                                text
                                        )
                )

                .toList();
    }


    private User
    getLoggedInUser() {

        String username =

                SecurityContextHolder

                        .getContext()

                        .getAuthentication()

                        .getName();

        return userRepository

                .findByUsername(

                        username
                )

                .orElseThrow();
    }
}