package com.mahdibou.aiAssistant.service;

import com.mahdibou.aiAssistant.entity.ConversationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final ConversationMemoryService memoryService;

    public String chat(String sessionId, String message) {

        List<ConversationMessage> history =
                memoryService.getConversationHistory(sessionId);

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are an AI assistant helping users with dog adoption.
                Always use previous conversation context.
                Answer clearly and concisely.
                """);

        for (ConversationMessage msg : history) {
            prompt.append("\n")
                    .append(msg.getRole())
                    .append(": ")
                    .append(msg.getContent());
        }

        prompt.append("\nUSER: ").append(message);
        prompt.append("\nASSISTANT:");

        String response = chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();

        memoryService.saveUserMessage(sessionId, message);
        memoryService.saveAssistantMessage(sessionId, response);

        return response;
    }
}
