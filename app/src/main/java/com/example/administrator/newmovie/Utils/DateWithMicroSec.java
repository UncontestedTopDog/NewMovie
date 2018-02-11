package com.example.administrator.newmovie.Utils;

import org.joda.time.DateTime;

/**
 * Created by huangweiliang on 18/02/11.
 * Des:带微秒数的时间
 */
public class DateWithMicroSec {
    public final DateTime time;
    public final int microseconds;

    public DateWithMicroSec(final DateTime time, final int microSec){
        this.time = time;
        this.microseconds = microSec;
    }
}
