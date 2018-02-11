package com.example.administrator.newmovie.Utils;

import java.util.regex.Pattern;

/**
 * Created by huangweiliang on 18/02/11.
 * Desc: 输入校验工具
 */
public class InputCheckUtil {

    private static final String PASSWORD_EXP = "^[\\S]{8,20}$";
    //    private static final String PHONE_EXP = "^(134|135|136|137|138|139|147|150|151|152|157|158|159|187|188|" + //移动
    //            "130|131|132|155|156|185|186|" + //联通
    //            "133|153|180|189|" + //电信
    //            "170)[\\d]{8}$"; //虚拟运营商
    private static final String PHONE_EXP = "^1[\\d]{10}$"; //虚拟运营商

    public static boolean isValidPassword(CharSequence input) {
        return Pattern.matches(PASSWORD_EXP, input);
    }

    public static boolean isValidPhoneNum(CharSequence input) {
        return Pattern.matches(PHONE_EXP, input);
    }

}
