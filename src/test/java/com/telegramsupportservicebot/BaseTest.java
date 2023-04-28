package com.telegramsupportservicebot;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.model.Message;

public abstract class BaseTest {
    protected static final Integer ID_DEFAULT = 1;
    protected static final Integer ID_ZERO = 0;

    protected static MessageResponseDto createMessageResponseDto() {
        return MessageResponseDto.builder()
                .id(1)
                .telegramID("580030336")
                .firstName("FailyFox")
                .message("One")
                .build();
    }
    protected static Message createMessage() {
        Message message = new Message();
        message.setId(100);
        message.setTelegramID("123456789");
        message.setFirstName("FirstName");
        message.setMessage("Message");
        return message;
    }
}