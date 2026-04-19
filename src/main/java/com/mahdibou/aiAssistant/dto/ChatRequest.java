package com.mahdibou.aiAssistant.dto;


import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        String sessionId,
        @NotBlank
        String message
) {
}
