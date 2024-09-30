package com.github.srilaxmi.com.util;

import com.github.srilaxmi.com.model.User;

import static com.github.srilaxmi.com.constants.GlobalConstants.TODOS_URL;

public class AutomationUtils {

    public static String getTodosUrl(User user) {
        return TODOS_URL +
                "?" +
                "userId" +
                "=" +
                user.getId();
    }

    public static Boolean isFancodeCityUser(User user) {

        if (user == null || user.getAddress() == null || user.getAddress().getGeo() == null) {
            return false; // Handle the case where user or address or geo is null
        }

        double latitude = getDoubleValue(user.getAddress().getGeo().getLat());
        double longitude = getDoubleValue(user.getAddress().getGeo().getLng());

        return latitude > -40 && latitude < 5 && longitude > 5 && longitude < 100;
    }

    public static double getDoubleValue(String value) {

        if (value.charAt(0) == '-') {
            return -1 * Double.parseDouble(value.substring(1));
        } else {
            return Double.parseDouble(value);
        }

    }


}
