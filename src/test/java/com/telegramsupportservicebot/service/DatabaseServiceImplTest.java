package com.telegramsupportservicebot.service;

import com.telegramsupportservicebot.BaseTest;
import com.telegramsupportservicebot.dto.request.MessageRequestDto;
import com.telegramsupportservicebot.dto.response.MessageResponseDto;
import com.telegramsupportservicebot.model.Message;
import com.telegramsupportservicebot.repository.MessageRepository;
import com.telegramsupportservicebot.service.impl.DatabaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseServiceImplTest extends BaseTest {
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private DatabaseServiceImpl databaseService;

    private Message message;

    @BeforeEach
    public void setupDto() {
        message = createMessage();
    }

    @Test
    public void saveMessage_thenReturnResponseDto() {
        databaseService.saveMessage(message);
        verify(messageRepository, times(1)).save(message);
    }
}