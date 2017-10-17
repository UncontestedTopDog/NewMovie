package com.example.administrator.newmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/10/17.
 */

public class GradeProgress extends View {
    Paint paint ;
    int width ,height,height2 ;
    int max = 100 ;
    int progress ;
    public GradeProgress(Context context) {
        super(context);
        init();
    }

    public GradeProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradeProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.icon_rocket);
        width = bitmap1.getWidth();// 宽度值
        height = MeasureSpec.getSize(heightMeasureSpec);// 高度值
        height2 = bitmap1.getHeight();
        Log.i("WWWWW",width+" "+height+" "+widthMeasureSpec+" "+heightMeasureSpec);
        setMeasuredDimension(width, height+height2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.rgb(55, 200, 255));// 设置画笔颜色
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(getResources().getColor(R.color.grade_progress_bg));                    //设置画笔颜色
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.FILL);                   //空心效果
        RectF r2 = new RectF();                           //RectF对象
        r2.left = width / 4;                                 //左边
        r2.top = 0;                                 //上边
        r2.right = width * 3 / 4;                                   //右边
        r2.bottom = height+height2;                              //下边
        canvas.drawRoundRect(r2, width / 4, width / 4, paint);        //绘制圆角矩形
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_rocket);
        if (!bitmap1.isRecycled())
            canvas.drawBitmap(bitmap1, 0, height - height * progress / max, paint);
        super.onDraw(canvas);
    }

    /** 设置progressbar进度 */
    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void showGrade() {
        final int i = progress;
        progress = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < i) {
                    progress++;
                    postInvalidate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

