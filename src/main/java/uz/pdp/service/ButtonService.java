package uz.pdp.service;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.model.Category;

import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utils.MessageKey.*;

public class ButtonService {

    private static I18nService i18n = I18nService.getInstance();

    public static ReplyKeyboardMarkup chooseLanguage() {

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton(i18n.getMsg(LANG_UZ));
        KeyboardButton button2 = new KeyboardButton(i18n.getMsg(LANG_EN));
        row1.add(button1);
        row1.add(button2);

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(List.of(row1));
        replyMarkup.setIsPersistent(true);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(true);
        return replyMarkup;
    }

    public static ReplyKeyboardMarkup sharePhoneNumber() {

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("📞");
        button1.setRequestContact(true);
        KeyboardButton button2 = new KeyboardButton(i18n.getMsg(BACK));
        row1.add(button1);
        row1.add(button2);

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(List.of(row1));
        replyMarkup.setIsPersistent(true);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(true);
        return replyMarkup;
    }


    public static ReplyKeyboard menu(List<Category> categories) {

        System.out.println(categories);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (int i = 0; i < categories.size(); i = i + 2) {
            KeyboardRow row = new KeyboardRow(2);
            row.add(categories.get(i).getName());

            if (i + 1 < categories.size())
                row.add(categories.get(i + 1).getName());

            keyboardRows.add(row);
        }

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setKeyboard(keyboardRows);
        replyMarkup.setIsPersistent(true);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(true);
        return replyMarkup;
    }
}
