package uz.pdp.service;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.model.enams.Lang;

import java.util.LinkedList;
import java.util.List;

public class ButtonService {

    public static ReplyKeyboardMarkup homeMenuButtons() {

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("Uz 🇺🇿");
        KeyboardButton button2 = new KeyboardButton("RU 🇷🇺");
        row1.add(button1);
        row1.add(button2);

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(List.of(row1));
        replyMarkup.setIsPersistent(true);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(true);
        return replyMarkup;
    }


}
