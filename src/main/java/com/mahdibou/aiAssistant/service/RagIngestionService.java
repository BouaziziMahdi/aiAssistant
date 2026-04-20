package com.mahdibou.aiAssistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RagIngestionService {
    private final VectorStore vectorStore;
    private final TextChunkingService chunkingService;

    public void ingest(String content , String source){
        List<String>chunks=chunkingService.chunk(content);
        List<Document> documents=chunks.stream().map(
                chunk-> new Document(
                        chunk,
                        Map.of("source", source)
                ))
                .toList();
        vectorStore.add(documents);
    }
}
