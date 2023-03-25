package com.telegramsupportservicebot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {
    private static final String THERE_IS_NO_MESSAGE_WITH_ID_MSG = "There is no message with id %d";

    public MessageNotFoundException(int id) {
        super(String.format(THERE_IS_NO_MESSAGE_WITH_ID_MSG, id));
    }
}