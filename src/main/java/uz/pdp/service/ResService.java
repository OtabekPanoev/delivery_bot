package uz.pdp.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.utils.GlobalVar;
import uz.pdp.utils.MessageKey;

@Slf4j
public class ResService {
    private static final I18nService i18n = I18nService.getInstance();

    public static void sendMsg(Long chatId, String text, ReplyKeyboard replyKeyboard) {

        try {
            GlobalVar.getMyBot().execute(
                    SendMessage.builder()
                            .text(text)
                            .chatId(chatId)
                            .replyMarkup(replyKeyboard)
                            .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public static void sendMsg(Long chatId, String text) {
        sendMsg(chatId, text, null);
    }

    public static void sendErrorMsg(Long chatId) {
        sendMsg(chatId, "Error!!");
    }


    public static void sendMsg(Long chatId, MessageKey key, ReplyKeyboard replyKeyboard) {
        sendMsg(chatId, i18n.getMsg(key), replyKeyboard);
    }


    public static void sendPhoto(Long chatId, MessageKey key, ReplyKeyboard replyKeyboard, InputFile inputFile) {
        try {
            GlobalVar.getMyBot().execute(
                    SendPhoto.builder()
                            .chatId(chatId)
                            .caption(i18n.getMsg(key))
                            .photo(inputFile)
                            .replyMarkup(replyKeyboard)
                            .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


}
