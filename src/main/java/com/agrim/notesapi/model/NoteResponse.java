//package com.agrim.notesapi.model;
//
//public class NoteResponse {
//
//    private int id;
//    private String text;
//
//    public NoteResponse(int id, String text) {
//        this.id = id;
//        this.text = text;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getText() {
//        return text;
//    }
//}
package com.agrim.notesapi.model;

import java.time.LocalDateTime;

public class NoteResponse {

    private int id;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public NoteResponse(
            int id,
            String text,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id =
                id;
        this.text =
                text;
        this.createdAt =
                createdAt;
        this.updatedAt =
                updatedAt;
    }
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public LocalDateTime
    getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime
    getUpdatedAt() {
        return updatedAt;
    }

}