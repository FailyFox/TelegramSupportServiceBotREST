package com.telegramsupportservicebot.service;

public interface DatabaseService {
    void saveMessage(String telegramID, String userFirstName, String userMessage);
}