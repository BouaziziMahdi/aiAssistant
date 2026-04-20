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
    private final RagRetrievalService ragRetrievalService;
    private final ToolRoutingService toolRoutingService;

    public String chat(String sessionId, String message) {

        // Fallback orchestration for scheduling
        if (toolRoutingService.shouldHandleScheduling(message)) {
            String toolResponse = toolRoutingService.tryHandleScheduling(message);

            memoryService.saveUserMessage(sessionId, message);
            memoryService.saveAssistantMessage(sessionId, toolResponse);

            return toolResponse;
        }

        List<ConversationMessage> history =
                memoryService.getConversationHistory(sessionId);

        String retrievedContext = ragRetrievalService.retrieveContext(message);

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are an AI assistant helping users with dog adoption.
                Use previous conversation context when relevant.
                If retrieved knowledge is available, use it.
                If the user asks to schedule a visit, use the scheduling tool when possible.
                Answer clearly and concisely.
                """);

        if (!retrievedContext.isBlank()) {
            prompt.append("\n\nRETRIEVED KNOWLEDGE:\n")
                    .append(retrievedContext)
                    .append("\n");
        }

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