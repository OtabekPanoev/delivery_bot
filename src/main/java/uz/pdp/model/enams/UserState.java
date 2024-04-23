package uz.pdp.model.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserState {
    USER_STARTED(1),
    CHOOSE_LANGUAGE(2),
    SHARE_PHONE_NUMBER(3),
    MENU(4);

    private final int step;

    public static UserState getByStep(int step) {
        return values()[step-1];
    }
}
