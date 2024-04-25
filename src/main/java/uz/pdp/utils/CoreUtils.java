package uz.pdp.utils;

public interface CoreUtils {

    static boolean checkPhoneNumber(String phone) {
        return phone.matches(Patterns.PHONE);
    }
}
