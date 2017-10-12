package com.example.administrator.newmovie;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by chenjh on 2015/9/24.
 * Desc:
 */
public class RoundedDrawableFactory {

    public static RoundedDrawable create(Context context, Bitmap bitmap, float cornerRadius, int margin) {
        return new RoundedDrawable(bitmap,cornerRadius,margin,DimensionUtil.targetDensity(context));
    }
}
