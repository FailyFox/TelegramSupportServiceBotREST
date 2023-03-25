package com.telegramsupportservicebot.mapper;

import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.model.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageResponseMapper {
    MessageResponseDto toDto(Message message);
    Message toMessage(MessageResponseDto messageResponseDto);
}