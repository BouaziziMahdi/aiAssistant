package com.mahdibou.aiAssistant.controller;

import com.mahdibou.aiAssistant.dto.ChatReponse;
import com.mahdibou.aiAssistant.dto.ChatRequest;
import com.mahdibou.aiAssistant.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    @PostMapping
    public ChatReponse chat(@RequestBody ChatRequest request) {

        String sessionId = request.sessionId();

        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }

        String response = chatService.chat(sessionId, request.message());

        return new ChatReponse(sessionId, response);
    }
}
