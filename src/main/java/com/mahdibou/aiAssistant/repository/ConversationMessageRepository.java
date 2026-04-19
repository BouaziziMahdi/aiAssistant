package com.mahdibou.aiAssistant.repository;

import com.mahdibou.aiAssistant.entity.ConversationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, String> {
    List<ConversationMessage> findTop20BySessionIdOrderByCreatedAtAsc(String sessionId);
}
