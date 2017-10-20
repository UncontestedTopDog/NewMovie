package com.example.administrator.newmovie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

/**
 * Created by Administrator on 2017/10/19.
 */

public class RoundImageView extends ImageView {

    private static final int CIRCLE = 1;
    private static final int RECTANGLE = 0;

    private int style;
    private int color;
    private int width;
    private float margin;
    private int margincolor;
    private int defaultWidth = 0, defaultHeight = 0;

    public RoundImageView(Context context) {
        this(context, null, 1);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        width = ta.getInt(R.styleable.RoundImageView_width, 1);
        color = ta.getColor(R.styleable.RoundImageView_color, Color.WHITE);
        style = ta.getInt(R.styleable.RoundImageView_style, CIRCLE);
        margin = ta.getFloat(R.styleable.RoundImageView_margin, 0);
        margincolor = ta.getInt(R.styleable.RoundImageView_margincolor, Color.GRAY);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        //如果图片为空，直接放返回
        if (drawable == null)
            return;
        //如果控件的宽高其中一个为0，直接返回
        if (getWidth() == 0 || getHeight() == 0)
            return;
        this.measure(0, 0);
        //如果是.9图也无法显示，直接返回
        if (drawable.getClass() == NinePatchDrawable.class)
            return;
        Bitmap b = null;
        try {
            b = ((BitmapDrawable) drawable).getBitmap();
        } catch (ClassCastException e) {
            b = ((GlideBitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        //获取控件的宽高
        if (defaultWidth == 0) {
            defaultWidth = getWidth();
        }
        if (defaultHeight == 0) {
            defaultHeight = getHeight();
        }
        //半径的等于宽高中较小的一边的一半
        int radius = (defaultHeight > defaultWidth ? defaultWidth : defaultHeight) / 2;
        Bitmap roundBitmap = handlePicture(bitmap, radius, style);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setFilterBitmap(true);//对位图进行滤波处理
        paint.setDither(true);//设置防抖动
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
        if (style == CIRCLE) {
            canvas.drawCircle(radius, radius, radius - (float) (width / 2), paint);
            if (margin != 0) {
                paint.setColor(margincolor);
                paint.setStrokeWidth(margin);
                canvas.drawCircle(radius, radius, radius - (float) (margin / 2), paint);
            }
        } else if (style == RECTANGLE) {
            Path path = new Path();
            path.moveTo(0, 0);
            path.lineTo(0, roundBitmap.getHeight());
            path.lineTo(roundBitmap.getWidth(), roundBitmap.getHeight());
            path.lineTo(roundBitmap.getWidth(), 0);
            path.lineTo(0, 0);
            canvas.drawPath(path, paint);
            if (margin != 0) {
                paint.setColor(margincolor);
                paint.setStrokeWidth(margin);
                canvas.drawPath(path, paint);
            }
        }

    }

    private Bitmap handlePicture(Bitmap bitmap, int radius, int style) {
        if (style == RECTANGLE)
            return Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
        Bitmap squareBitmap;
        int x = 0, y = 0;
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int squareWidth = 0, squareHeight = 0;
        //以bitmap的短的边的标准，截取一个以bitmap的中心为中心的正方形
        if (bitmapWidth > bitmapHeight) {
            x = bitmapWidth / 2 - bitmapHeight / 2;
            y = 0;
            squareHeight = squareWidth = bitmapHeight;
        } else {
            y = bitmapHeight / 2 - bitmapWidth / 2;
            x = 0;
            squareHeight = squareWidth = bitmapWidth;
        }
        squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        //将squareBitmap缩放成diameter的大小
        squareBitmap = Bitmap.createScaledBitmap(squareBitmap, radius * 2, radius * 2, false);
        //新建一个bitmap resultbitmap，大小为squareBitmap的大小
        Bitmap resultbitmap = Bitmap.createBitmap(squareBitmap.getWidth(),
                squareBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultbitmap);
        Paint paint = new Paint();
        //新建一个矩形区域位置为0, 0, squareBitmap.getWidth(),squareBitmap.getHeight()
        Rect rect = new Rect(0, 0, squareBitmap.getWidth(), squareBitmap.getHeight());
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setFilterBitmap(true);//对位图进行滤波处理
        paint.setDither(true);//设置防抖动
        canvas.drawARGB(0, 0, 0, 0);//画背景颜色为透明
        //以（squareBitmap.getWidth() / 2,squareBitmap.getHeight() / 2）为圆心，squareBitmap.getWidth() / 2为半径画圆
        canvas.drawCircle(squareBitmap.getWidth() / 2,
                squareBitmap.getHeight() / 2,
                squareBitmap.getWidth() / 2,
                paint);
        //精髓，因为这句话，canvas原先画的透明背景圆和接下来的squareBitmap重叠部分显示出来
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //在画布中将squareBitmap画出来
        canvas.drawBitmap(squareBitmap, rect, rect, paint);
        bitmap = null;
        squareBitmap = null;
        //将画布中的内容返回
        return resultbitmap;
    }

}
