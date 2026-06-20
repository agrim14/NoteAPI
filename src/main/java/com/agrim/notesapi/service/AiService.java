package com.agrim.notesapi.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AiService {

    private final WebClient webClient;
    public AiService(
            @Value("${groq.api.key}")
            String apiKey
    ) {
        this.webClient =
                WebClient.builder()
                        .baseUrl(
                                "https://api.groq.com/openai/v1"
                        )
                        .defaultHeader(
                                HttpHeaders.AUTHORIZATION,
                                "Bearer " + apiKey
                        )
                        .build();
    }
    public String summarize(
            String text
    ) {
        Map<String,Object> body =
                Map.of(
                        "model",
                        "llama-3.1-8b-instant",
                        "messages",
                        new Object[]{
                                Map.of(
                                        "role",
                                        "user",
                                        "content",
                                        "Summarize in one short sentence:\n\n"
                                                +
                                                text
                                )
                        }
                );
        Map response =
                webClient
                        .post()
                        .uri(
                                "/chat/completions"
                        )
                        .bodyValue(
                                body
                        )
                        .retrieve()
                        .bodyToMono(
                                Map.class
                        )
                        .block();
        var choices =
                (java.util.List)
                        response.get(
                                "choices"
                        );
        var first =
                (Map)
                        choices.get(0);
        var message =
                (Map)
                        first.get(
                                "message"
                        );
        return
                message
                        .get(
                                "content"
                        )
                        .toString();
    }

}