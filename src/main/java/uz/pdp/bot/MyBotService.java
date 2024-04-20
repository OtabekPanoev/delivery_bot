package uz.pdp.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBotService {
    private final TelegramLongPollingBot bot;

    public MyBotService(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public void onUpdateReceived(Update update) throws Exception {
        Message message = update.getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bot Thread" + Thread.currentThread().getName());

        bot.execute(sendMessage);
    }
}
