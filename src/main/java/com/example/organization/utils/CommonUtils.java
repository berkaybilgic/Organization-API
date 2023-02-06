package com.example.organization.utils;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static String normalizedString(String str) {

        String normalizedString = Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9]", " ")
                .replaceAll("\\s", "")
                .toLowerCase()
                .trim();

        return normalizedString;
    }

    public static boolean isValidEmail(String email) {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
