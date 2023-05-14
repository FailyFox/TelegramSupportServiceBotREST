package com.telegramsupportservicebot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.telegramsupportservicebot.BaseTest;
import com.telegramsupportservicebot.dto.request.MessageRequestDto;
import com.telegramsupportservicebot.service.impl.DatabaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BotServiceTest extends BaseTest {
    @Mock
    private DatabaseServiceImpl databaseService;
    @Mock
    private TelegramBotsApi telegramBotsApi;
    @InjectMocks
    private BotService botService;

    @Autowired
    private MockMvc mockMvc;

    private MessageRequestDto messageRequestDto;

    @BeforeEach
    public void setupDto() {
        messageRequestDto = createMessageRequestDto();
    }

    @Test
    public void onUpdateReceived_thenSaveMessageToDatabase() {
        Update update = new Update();
        Chat chat = new Chat();
        chat.setId(123456789L);
        Message message = new Message();
        message.setChat(chat);
        message.setFrom(new User(123456789L, "FirstName", false));
        message.setText("Hello, world!");
        update.setMessage(message);
        botService.onUpdateReceived(update);
        verify(databaseService).saveMessage(any(com.telegramsupportservicebot.model.Message.class));
    }
    @Test
    public void onUpdateReceived_thenNotSaveMessageToDatabase() {
        Update update = new Update();
        Chat chat = new Chat();
        chat.setId(123456789L);
        Message message = new Message();
        message.setAudio(new Audio());
        update.setMessage(message);
        botService.onUpdateReceived(update);
        verify(databaseService, never()).saveMessage(any(com.telegramsupportservicebot.model.Message.class));
    }
    @Test
    public void startBot() throws TelegramApiException {
        when(telegramBotsApi.registerBot(botService)).thenReturn(null);
        botService.startBot();
        verify(telegramBotsApi).registerBot(botService);
    }
    @Test
    public void sendMessage_thenReturnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(messageRequestDto);
        mockMvc.perform(post("/message/send/{userId}", TELEGRAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}