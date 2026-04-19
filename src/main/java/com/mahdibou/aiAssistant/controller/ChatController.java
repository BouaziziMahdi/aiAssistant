package com.mahdibou.aiAssistant.controller;

import com.mahdibou.aiAssistant.dto.ChatReponse;
import com.mahdibou.aiAssistant.dto.ChatRequest;
import com.mahdibou.aiAssistant.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    public  final ChatService chatService ;
    @PostMapping
    public  ChatReponse chat(@Valid @RequestBody ChatRequest request){
        String answer=chatService.ask(request.message());
        return  new ChatReponse(answer);
    }
}
