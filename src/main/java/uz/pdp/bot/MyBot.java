package uz.pdp.bot;


import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyBot extends TelegramLongPollingBot {

    Executor executor = Executors.newFixedThreadPool(200);
    private MyBotService myBotService = new MyBotService(this);
    public MyBot(String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return BotProperty.USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {

        executor.execute(() -> {
            try {
                myBotService.onUpdateReceived(update);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }


}
