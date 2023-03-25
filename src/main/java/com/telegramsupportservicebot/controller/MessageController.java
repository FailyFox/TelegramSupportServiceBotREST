package com.telegramsupportservicebot.controller;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{id}")
    public MessageResponseDto getMessage(@PathVariable Integer id) {
        return messageService.getMessage(id);
    }
    @GetMapping
    public List<MessageResponseDto> getAllMessages() {
        return messageService.getAllMessages();
    }
}