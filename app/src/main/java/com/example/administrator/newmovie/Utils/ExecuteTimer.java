package com.example.administrator.newmovie.Utils;

/**
 * Created by huangweiliang on 18/02/11.
 * Des:计时器
 */
public class ExecuteTimer {

    long mStartTimer = 0;
    long mEndTimer = 0;

    public void start() {
        mStartTimer =  System.currentTimeMillis();
    }

    public void end() {
        mEndTimer =  System.currentTimeMillis();
    }

    public long diff() {
        return mEndTimer - mStartTimer;
    }
}
