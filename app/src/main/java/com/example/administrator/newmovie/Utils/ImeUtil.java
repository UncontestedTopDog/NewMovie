package com.example.administrator.newmovie.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by huangweiliang on 18/02/11.
 * Des:输入法键盘的控制器
 */
public final class ImeUtil {

    public static void hideIME(Activity context) {
        final View view = context.getCurrentFocus();
        hideIME(context,view);
    }

    public static void hideIME(Context context, View view) {
        if (view == null) {
            return;
        }
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showIME(Context context, View view) {
        if (view != null && view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0);
        }
    }
}
