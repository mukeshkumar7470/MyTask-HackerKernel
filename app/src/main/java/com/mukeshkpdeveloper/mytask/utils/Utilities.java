package com.mukeshkpdeveloper.mytask.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Utilities {
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isAlphanumeric(String str){
        String pattern = "^[a-zA-Z0-9// ]+$";
        return !TextUtils.isEmpty(str) && str.matches(pattern);
    }
}
