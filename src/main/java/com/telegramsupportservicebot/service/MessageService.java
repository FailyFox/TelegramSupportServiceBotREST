package com.telegramsupportservicebot.service;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {
    MessageResponseDto getMessage(int id);
    List<MessageResponseDto> getAllMessages();
}