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
            @Value("${groq.api.key}") String apiKey
    ) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public String summarize(String text) {
        // Advanced System Instructions to make the AI adaptive and intelligent
        String systemInstruction = "You are an intuitive, sharp AI note-taking companion. Analyze the given text input. "
                + "If it is a long-form note or article, provide a brilliant, concise summary. "
                + "If it is an incomplete thought, creative writing piece, or song line, adapt instantly: "
                + "complete the line, jam along with it, or offer creative/logical advice instead of a robotic refusal. "
                + "Keep your entire response restricted to 1-3 highly engaging sentences maximum.";

        Map<String, Object> body = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", new Object[]{
                        Map.of("role", "system", "content", systemInstruction),
                        Map.of("role", "user", "content", text)
                }
        );

        Map response = webClient
                .post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || !response.containsKey("choices")) {
            return "My apologies, I couldn't connect to the core brain module right now.";
        }

        var choices = (java.util.List) response.get("choices");
        var first = (Map) choices.get(0);
        var message = (Map) first.get("message");

        return message.get("content").toString();
    }
}