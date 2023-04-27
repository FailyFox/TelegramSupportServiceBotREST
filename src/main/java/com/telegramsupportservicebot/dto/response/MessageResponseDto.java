package com.telegramsupportservicebot.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {
    private Integer id;
    private String telegramID;
    private String firstName;
    private String message;
}