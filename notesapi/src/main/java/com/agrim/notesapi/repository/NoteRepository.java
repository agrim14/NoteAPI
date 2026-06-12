package com.agrim.notesapi.repository;

import com.agrim.notesapi.model.Note;
import com.agrim.notesapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository
        extends JpaRepository<Note, Integer> {

    List<Note> findByText(String text);

    List<Note> findByTextContaining(String text);

    List<Note> findByUser(User user);

    @Query(
            "SELECT n FROM Note n WHERE n.text = :text"
    )
    List<Note> getNotesByExactText(
            @Param("text") String text
    );
}