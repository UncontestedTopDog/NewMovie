package com.example.administrator.newmovie.Utils;

import android.text.format.Time;

/**
 * Created by Administrator on 2017/10/20.
 */

public class TimeUtil {
    public static String getCurrentTime(){
        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        String s = t.year+"";
        s+= t.month+"";
        s+= t.monthDay+"";
        s+= t.hour+""; // 0-23
        s+= t.minute+"";
        s+= t.second+"";
        return s;
    }
}
