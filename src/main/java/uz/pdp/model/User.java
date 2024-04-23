package uz.pdp.model;

import uz.pdp.model.enams.Lang;
import uz.pdp.model.enams.Role;
import uz.pdp.model.enams.UserState;

public class User {
    private Long chatId; // unique
    private String username;
    private String fio;
    private String phoneNumber;
    private Role role;
    private Lang lang;
    private UserState userState;
}
