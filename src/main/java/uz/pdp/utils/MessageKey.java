package uz.pdp.utils;

import lombok.Getter;

@Getter
public enum MessageKey {
    LANG_EN("lang.en", "En \uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"),
    LANG_UZ( "lang.uz", "Uz \uD83C\uDDFA\uD83C\uDDFF"),
    STARTED_MSG("started.msg", "Assalomu Aleykum. Выберите одно из следующих"),
    SHARE_PHONE_NUMBER("share.phone", "Telefon Raqamingizni Kiriting"),
    BACK("back", "Ortga"),
    MAIN_MENU("main.menu", "G38 FastFoot Botga Xush keldingiz. Kategoriyalardan birini tanlang"); // todo

    private String key; // unique
    private String val;

    MessageKey(String key, String val) {
        this.key = key;
        this.val = val;
    }
}
