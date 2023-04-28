package com.telegramsupportservicebot.service;

import com.telegramsupportservicebot.model.Message;

public interface DatabaseService {
    void saveMessage(Message message);
}