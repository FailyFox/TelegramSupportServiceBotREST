package com.telegramsupportservicebot.service.impl;

import com.telegramsupportservicebot.model.Message;
import com.telegramsupportservicebot.repository.MessageRepository;
import com.telegramsupportservicebot.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {
    private final MessageRepository messageRepository;
    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}