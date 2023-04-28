package com.telegramsupportservicebot;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;

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
}