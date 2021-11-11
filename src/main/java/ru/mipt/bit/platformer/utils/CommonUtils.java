package ru.mipt.bit.platformer.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CommonUtils {
    public static <T> String loadFile(String filePath, Class<T> clazz) {
        try {
            return new String(Objects.requireNonNull(clazz.getResourceAsStream("/" + filePath)).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> splitFile(String file) {
        if (file.contains("\r\n")) {
            return List.of(file.split("\r\n"));
        } else if (file.contains("\n")) {
            return List.of(file.split("\n"));
        }

        return new ArrayList<>();
    }

    public static void checkStringLength(String name, String value, int length) {
        if (value.length() != length) {
            throw new IllegalArgumentException(name + " has wrong length: "
                    + value.length() + " expected:" + length);
        }
    }

    public static void checkStringLength(String value, int length) {
        if (value.length() != length) {
            throw new IllegalArgumentException("String has wrong length: "
                    + value.length() + " expected:" + length);
        }
    }

    public static void checkNotNull(String name, Object value) {
        if (value == null || (value.getClass() == String.class && ((String) value).isEmpty())) {
            throw new IllegalArgumentException(name + " content is null");
        }
    }
}
