package com.mahdibou.aiAssistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class RagRetrievalService
{ private final VectorStore vectorStore;
    public String retrieveContext(String query)
    { List<Document> documents = vectorStore.similaritySearch(
            SearchRequest.builder() .query(query) .topK(3) .build() );
        if (documents == null || documents.isEmpty()) { return ""; }
        return documents.stream().map(Document::getText).collect(Collectors.joining("\n\n")); } }