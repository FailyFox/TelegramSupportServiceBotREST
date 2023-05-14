package com.telegramsupportservicebot.service;

import com.telegramsupportservicebot.BaseTest;
import com.telegramsupportservicebot.model.Message;
import com.telegramsupportservicebot.repository.MessageRepository;
import com.telegramsupportservicebot.service.impl.DatabaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
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
        verify(messageRepository).save(message);
    }
}