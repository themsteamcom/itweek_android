package com.example.themsteam.itweekdemo.utils;

/**
 * Created by vshabanov on 16-Aug-17.
 */

public class StringUtils {
    static public boolean isEmptyOrNull(String str) {
        return (str == null || str.equals(""));
    }

    static public String append(String str1, String str2) {
        if (isEmptyOrNull(str1))
            return str2;
        else
            return str1 + " " + str2;
    }
}
