package com.agrim.notesapi.model;

public class AiResponse {

    private String summary;
    public AiResponse(
            String summary
    ) {
        this.summary =
                summary;
    }
    public String getSummary() {
        return summary;
    }

}