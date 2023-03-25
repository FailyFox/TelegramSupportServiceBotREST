package com.telegramsupportservicebot.service;

public interface DatabaseService {
    void saveMessage(Long userId, String userFirstName, String userMessage);
}