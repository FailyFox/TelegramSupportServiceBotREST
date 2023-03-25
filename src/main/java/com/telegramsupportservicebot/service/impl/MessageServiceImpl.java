package com.telegramsupportservicebot.service.impl;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.exception.MessageNotFoundException;
import com.telegramsupportservicebot.mapper.MessageResponseMapper;
import com.telegramsupportservicebot.model.Message;
import com.telegramsupportservicebot.repository.MessageRepository;
import com.telegramsupportservicebot.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageResponseMapper messageResponseMapper;
    @Override
    public MessageResponseDto getMessage(int id) {
        return messageRepository.findById(id)
                .map(messageResponseMapper::toDto)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }
    @Override
    public List<MessageResponseDto> getAllMessages() {
        return messageRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Message::getMessage))
                .map(messageResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}