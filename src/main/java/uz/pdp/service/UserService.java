package uz.pdp.service;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.model.Category;
import uz.pdp.model.User;
import uz.pdp.model.enams.Lang;
import uz.pdp.model.enams.Role;
import uz.pdp.model.enams.UserState;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.repository.UserRepository;
import uz.pdp.utils.CoreUtils;
import uz.pdp.utils.FileUrls;
import uz.pdp.utils.GlobalVar;
import uz.pdp.utils.MessageKey;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static uz.pdp.utils.MessageKey.*;

public class UserService {
    @Getter
    private static final UserService instance = new UserService();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();
    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();
    private UserService(){}

    public User userVerify(Long chatId) {
        Optional<User> optional = userRepository.findById(chatId);
        if (optional.isEmpty()) {
            User user = User.builder()
                    .chatId(chatId)
                    .userState(UserState.USER_STARTED)
                    .role(Role.USER)
                    .lang(Lang.UZ)
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
        ResService.sendMsg(user.getChatId(), STARTED_MSG, ButtonService.chooseLanguage());
    }

    public void setUserLang(Update update, Message message) {
        User user = GlobalVar.getUSER();
        String text = message.getText();

        if (!setLang(text, user)) {
            ResService.sendErrorMsg(user.getChatId());
            return;
        }

        user.setUserState(UserState.SHARE_PHONE_NUMBER);
        userRepository.update(user.getChatId(), user);

        ResService.sendMsg(user.getChatId(), SHARE_PHONE_NUMBER, ButtonService.sharePhoneNumber());
    }

    public void setPhoneNumber(Update update, Message message) {
        User user = GlobalVar.getUSER();
        String phone;
        if (message.hasContact())
            phone = message.getContact().getPhoneNumber();
        else
            phone = message.getText();

        if (!CoreUtils.checkPhoneNumber(phone))
            ResService.sendErrorMsg(user.getChatId());

        user.setPhoneNumber(phone);
        user.setUserState(UserState.MENU);
        userRepository.update(user.getChatId(), user);

        List<Category> categories = categoryRepository.findAll();
        InputFile inputFile = new InputFile(new File(FileUrls.MENU_PHOTO));

        ResService.sendPhoto(user.getChatId(), MAIN_MENU, ButtonService.menu(categories), inputFile);
    }


    private boolean setLang(String text, User user) {
        if (Objects.equals(text, LANG_UZ.getVal()))
            user.setLang(Lang.UZ);
        else if (Objects.equals(text, LANG_EN.getVal()))
            user.setLang(Lang.EN);
        else
            return false;
        return true;
    }
}
