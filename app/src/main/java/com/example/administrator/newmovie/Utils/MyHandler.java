package com.example.administrator.newmovie.Utils;

import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;

/**
 * Created by huqiuyun on 15/9/12.
 */
public class MyHandler<T> extends Handler {

    private final WeakReference<T> _outer;

    public MyHandler(T t) {
        super();
        _outer = new WeakReference<>(t);
    }

    public MyHandler(T t,Looper looper) {
        super(looper);
        _outer = new WeakReference<>(t);
    }
    public boolean isExist() {return _outer.get() != null;}
    public T get() { return _outer.get(); }
}
