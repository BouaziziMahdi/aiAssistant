package com.mahdibou.aiAssistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RagIngestionService {
    private final VectorStore vectorStore;

    public void ingest(String content , String source){
        Document document=new Document(
                content, Map.of("source",source)
        );
        vectorStore.add(List.of(document));
    }
}
