package com.telegramsupportservicebot;

import com.telegramsupportservicebot.controller.BotController;
import com.telegramsupportservicebot.service.impl.DatabaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@Slf4j
public class TelegramSupportServiceBotRESTApplication {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.execute(() -> {
			SpringApplication.run(TelegramSupportServiceBotRESTApplication.class, args);
		});

		executorService.execute(() -> {
			TelegramBotsApi telegramBotsApi = null;
			try {
				telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			} catch (TelegramApiException e) {
				throw new RuntimeException(e);
			}
			try {
				log.info("Registering bot...");
				telegramBotsApi.registerBot(new BotController(new DatabaseServiceImpl()));
				log.info("Telegram bot is ready to accept updates from user.....");
			} catch (TelegramApiException e) {
				log.error("Failed to register bot(check internet connection/bot token" +
						" or make sure only one instance of bot is running)", e);
			}
		});

		executorService.shutdown();
	}
}