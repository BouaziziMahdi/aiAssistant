package com.mahdibou.aiAssistant.service;

import com.mahdibou.aiAssistant.entity.ConversationMessage;
import com.mahdibou.aiAssistant.repository.ConversationMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationMemoryService {
    private final ConversationMessageRepository repository;

    public void saveUserMessage(String sessionId, String content) {
        repository.save(ConversationMessage.builder()
                .sessionId(sessionId)
                .role("USER")
                .content(content)
                .createdAt(LocalDateTime.now())
                .build());
    }

    public void saveAssistantMessage(String sessionId, String content) {
        repository.save(ConversationMessage.builder()
                .sessionId(sessionId)
                .role("ASSISTANT")
                .content(content)
                .createdAt(LocalDateTime.now())
                .build());
    }

    public List<ConversationMessage> getConversationHistory(String sessionId) {
        return repository.findTop20BySessionIdOrderByCreatedAtAsc(sessionId);
    }
}
