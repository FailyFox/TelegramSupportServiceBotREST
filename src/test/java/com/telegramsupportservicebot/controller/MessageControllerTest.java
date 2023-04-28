package com.telegramsupportservicebot.controller;

import com.telegramsupportservicebot.BaseTest;
import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest extends BaseTest {
    @Mock
    private MessageServiceImpl messageService;
    @Autowired
    private MockMvc mockMvc;

    private MessageResponseDto messageResponseDto;

    @BeforeEach
    public void setupDto() {
        messageResponseDto = createMessageResponseDto();
    }

    @Test
    public void getMessageById_thenReturnResponseDto() throws Exception {
        when(messageService.getMessage(anyInt())).thenReturn(messageResponseDto);
        mockMvc.perform(get("/message/{messageId}", ID_DEFAULT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(messageResponseDto.getId()))
                .andExpect(jsonPath("$.telegramID").value(messageResponseDto.getTelegramID()))
                .andExpect(jsonPath("$.firstName").value(messageResponseDto.getFirstName()))
                .andExpect(jsonPath("$.message").value(messageResponseDto.getMessage()));
    }
    @Test
    public void getMessageById_thenReturnNoSuchEntity() throws Exception {
        when(messageService.getMessage(anyInt())).thenReturn(messageResponseDto);
        mockMvc.perform(get("/message/{messageId}", ID_ZERO))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    public void getAllMessages_thenReturnResponseDto() throws Exception {
        when(messageService.getMessage(anyInt())).thenReturn(messageResponseDto);
        mockMvc.perform(get("/message"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(6))
                .andExpect(jsonPath("$[0].telegramID").value("454829651"))
                .andExpect(jsonPath("$[0].firstName").value("Vitalik"))
                .andExpect(jsonPath("$[0].message").value("Thu"));
    }
}