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
    int w ,h,h2,h3,h4 ;
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_rocket);
        w = bitmap.getWidth();// 宽度值
        h = MeasureSpec.getSize(heightMeasureSpec);// 高度值
        h2 = bitmap.getHeight() ;
        h4 = h - w ;
        h3 = h4 - h2 ;
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.rgb(55, 200, 255));// 设置画笔颜色
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(getResources().getColor(R.color.grade_progress_bg));                    //设置画笔颜色
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.FILL);                   //空心效果

        RectF r2 = new RectF();                           //RectF对象
        r2.left = w / 4;                                 //左边
        r2.top = 0;                                 //上边
        r2.right = w * 3 / 4;                                   //右边
        r2.bottom = h4;                              //下边
        canvas.drawRoundRect(r2, w / 4, w / 4, paint);        //绘制圆角矩形

        RectF r3 = new RectF();                           //RectF对象
        r3.left = w / 4;                                 //左边
        r3.top = h3 - h3 * progress / max;                                 //上边
        r3.right = w * 3 / 4;                                   //右边
        r3.bottom = h4;                              //下边
        paint.setColor(getResources().getColor(R.color.light_orange));
        canvas.drawRoundRect(r3, w / 4, w / 4, paint);        //绘制圆角矩形

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_rocket);
        if (!bitmap.isRecycled())
            canvas.drawBitmap(bitmap, 0, h3 - h3 * progress / max , paint);

//        paint.setColor(Color.BLUE);
//        RectF r4 = new RectF(0, h4, w, h);
//        canvas.drawRect(r4, paint);
//        paint.setColor(Color.RED);
//        paint.setTextSize(w);
//        canvas.drawText(progress+"", 0, h4+paint.getTextSize(), paint);

        Rect targetRect = new Rect(0, h4, w, h);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(50);
        String testString = progress+"";
//        paint.setColor(Color.CYAN);
//        canvas.drawRect(targetRect, paint);
        paint.setColor(Color.RED);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(testString, targetRect.centerX(), baseline, paint);
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
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

