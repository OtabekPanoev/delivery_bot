package uz.pdp.service;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.User;
import uz.pdp.model.enams.Role;
import uz.pdp.model.enams.UserState;
import uz.pdp.repository.UserRepository;
import uz.pdp.utils.GlobalVar;

import java.util.Optional;

public class UserService {
    @Getter
    private static final UserService instance = new UserService();
    private final UserRepository userRepository = UserRepository.getInstance();
    private UserService(){}

    public User userVerify(Long chatId) {
        Optional<User> optional = userRepository.findById(chatId);
        if (optional.isEmpty()) {
            User user = User.builder()
                    .chatId(chatId)
                    .userState(UserState.USER_STARTED)
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            return user;
        }
        return optional.get();
    }

    public void userStarted(Update update, Message message) {

        User user = GlobalVar.getUSER();
        user.setUserState(UserState.CHOOSE_LANGUAGE);
        userRepository.update(user.getChatId(), user);

        try {
            SendMessage sendMessage = SendMessage.builder()
                    .text("Salom Tilni tanlang")
                    .chatId(message.getChatId())
                    .replyMarkup(ButtonService.homeMenuButtons())
                    .build();
            GlobalVar.getMyBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
