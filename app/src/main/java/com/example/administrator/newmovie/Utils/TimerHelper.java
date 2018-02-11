package com.example.administrator.newmovie.Utils;

/**
 * Created by huqiuyun on 15/9/8.
 */
public class TimerHelper {

    public static void waitFor(long time) {
        if (time <= 0) return;

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
