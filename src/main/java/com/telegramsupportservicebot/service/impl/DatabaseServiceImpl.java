package com.telegramsupportservicebot.service.impl;

import com.telegramsupportservicebot.model.Message;
import com.telegramsupportservicebot.repository.MessageRepository;
import com.telegramsupportservicebot.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public void saveMessage(String telegramID, String userFirstName, String userMessage) {
        Message message = new Message();
        message.setTelegramID(telegramID);
        message.setFirstName(userFirstName);
        message.setMessage(userMessage);
        messageRepository.save(message);
    }
}