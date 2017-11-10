package com.example.administrator.newmovie.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ScoreView extends View {
    private static final String TAG = "ScoreView";
    int width;
    int height;
    int firstX;
    int secondX;
    int firstY;
    int secondY;
    int firstSize;
    int secondSize;

    public ScoreView(Context context) {
        super(context);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        firstX = 0 ;
        firstY = 0 ;
        secondX = width*3/5 ;
        secondY = 0 ;
        firstSize = width*3/5 ;
        secondSize = width*2/5;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.android_font);
        Bitmap bitmap = Bitmap.createBitmap(source, 7 * source.getWidth() / 10, 0, source.getWidth() / 10, source.getHeight());
        bitmap = Bitmap.createScaledBitmap(bitmap, firstSize, firstSize*bitmap.getHeight()/bitmap.getWidth(), false);
        canvas.drawBitmap(bitmap, firstX, firstY, null);
        bitmap = Bitmap.createBitmap(source, 8 * source.getWidth() / 10, 0, source.getWidth() / 10, source.getHeight());
        bitmap = Bitmap.createScaledBitmap(bitmap, secondSize, secondSize*bitmap.getHeight()/bitmap.getWidth(), false);
        canvas.drawBitmap(bitmap, secondX, secondY, null);
    }
}
