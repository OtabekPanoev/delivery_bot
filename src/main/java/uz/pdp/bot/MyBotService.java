package uz.pdp.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.model.User;
import uz.pdp.model.enams.UserState;
import uz.pdp.service.UserService;
import uz.pdp.utils.GlobalVar;

public class MyBotService {
    private final MyBot bot;
    private final UserService userService = UserService.getInstance();
    public MyBotService(MyBot bot) {this.bot = bot;}

    public void onUpdateReceived(Update update) throws Exception {

        GlobalVar.setMyBot(bot);

        Message message;
        if (update.hasMessage()) {
            message = update.getMessage();
        } else if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage();
            message.setText(update.getCallbackQuery().getData());
        } else return;

        User user = userService.userVerify(message.getChatId());
        GlobalVar.setUSER(user);

        cases(update, message, user.getUserState());
    }

    private void cases(Update update, Message message, UserState userState) {
        switch (userState) {
            case USER_STARTED -> userService.userStarted(update, message);
            case CHOOSE_LANGUAGE -> userService.setUserLang(update, message);
            case SHARE_PHONE_NUMBER -> userService.setPhoneNumber(update, message);
            default -> {
                return;
            }
        }
    }


}
