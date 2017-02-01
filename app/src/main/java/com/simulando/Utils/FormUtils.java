package com.simulando.Utils;

import com.simulando.Consts.AppConsts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luciano José on 31/01/2017.
 */

public class FormUtils {

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(AppConsts.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }

}
