package com.mahdibou.aiAssistant.config;

import com.mahdibou.aiAssistant.tool.ScheduleVisitTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    @Bean
    ChatClient chatClient(OllamaChatModel model, ScheduleVisitTool scheduleVisitTool) {
        return ChatClient.builder(model)
                .defaultTools(scheduleVisitTool)
                .build();
    }
}
