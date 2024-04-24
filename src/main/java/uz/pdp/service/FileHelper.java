package uz.pdp.service;

import com.google.gson.reflect.TypeToken;
import uz.pdp.utils.GlobalVar;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {

    public static <E> List<E> load(String url, Type type) {
        try {
            byte[] bytes = Files.readAllBytes(Path.of(url));
            String json = new String(bytes);
            return GlobalVar.GSON.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> void write(String url, List<E> data) {
        try {
            String json = GlobalVar.GSON.toJson(data);
            Files.writeString(Path.of(url), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
