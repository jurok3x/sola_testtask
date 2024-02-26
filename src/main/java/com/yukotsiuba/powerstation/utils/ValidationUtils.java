package com.yukotsiuba.powerstation.utils;

public class ValidationUtils {
    public static boolean isEmpty(Object value) {
        return value == null || (value instanceof String && ((String) value).trim().isEmpty());
    }

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
