package com.mahdibou.aiAssistant.controller;

import com.mahdibou.aiAssistant.dto.IngestRequest;
import com.mahdibou.aiAssistant.service.RagIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rag")
public class RagController {
    private final RagIngestionService ingestionService;

    @PostMapping("/ingest")
    public String ingest(@RequestBody IngestRequest request) {
        ingestionService.ingest(request.content(), request.source());
        return "Document ingested successfully";
    }
}
