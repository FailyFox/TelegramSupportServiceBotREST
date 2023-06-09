package com.telegramsupportservicebot.service;

import com.telegramsupportservicebot.dto.request.MessageRequestDto;
import com.telegramsupportservicebot.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BotService extends TelegramLongPollingBot {
    private final DatabaseService databaseService;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            Message message = new Message();
            message.setTelegramID(update.getMessage().getChatId().toString());
            message.setFirstName(update.getMessage().getFrom().getFirstName());
            message.setMessage(update.getMessage().getText());

            log.info("ID: {}, Name: {}, Message: {}", message.getId(), message.getFirstName(), message.getMessage());

            databaseService.saveMessage(message);
        }
        else {
            log.warn("Unexpected update from user");
        }
    }
    @PostMapping("/start")
    @ResponseStatus(HttpStatus.CREATED)
    public void startBot() {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        try {
            log.info("Registering bot...");
            telegramBotsApi.registerBot(this);
            log.info("Telegram bot is ready to accept updates from user.....");
        } catch (TelegramApiException e) {
            log.error("Failed to register bot(check internet connection/bot token" +
                    " or make sure only one instance of bot is running)", e);
        }
    }
    @PostMapping("/message/send/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@PathVariable Long userId, @RequestBody MessageRequestDto messageRequestDto) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(userId.toString())
                .text(messageRequestDto.getMessage())
                .build();
        try {
            this.sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Exception: ", e);
        }
    }
    @Override
    public String getBotUsername() {
        return "FailyFoxTestBot";
    }
    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}