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

    private int style;
    private int color;
    private int width;
    private BitmapPool bitmapPool;
    private int defaultWidth = 0 , defaultHeight = 0 ;

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
        style = ta.getInt(R.styleable.RoundImageView_style, 1);
        Log.i("ASDADASDA",width+"");
        ta.recycle();
    }

    private Bitmap handlePicture(Bitmap picture, int radius) {
        Bitmap resultBitmap = null;
        int x = 0, y = 0;
        int bitmapsize = picture.getWidth() > picture.getHeight() ? picture.getHeight() : picture.getWidth();
        if (picture.getWidth() > picture.getHeight())
            x = picture.getWidth() / 2 - picture.getHeight() / 2;
        else
            y = picture.getHeight() / 2 - picture.getWidth() / 2;
        picture = Bitmap.createBitmap(picture, x, y, bitmapsize, bitmapsize);
        picture = Bitmap.createScaledBitmap(picture, radius * 2, radius * 2, false);
        resultBitmap = Bitmap.createBitmap(bitmapsize, bitmapsize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        Paint paint = new Paint();
        //新建一个矩形区域位置为0, 0, squareBitmap.getWidth(),squareBitmap.getHeight()
        Rect rect = new Rect(0, 0, radius * 2, radius * 2);
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setFilterBitmap(true);//对位图进行滤波处理
        paint.setDither(true);//设置防抖动
        canvas.drawARGB(0, 0, 0, 0);//画背景颜色为透明
        if (style == 1) {
            canvas.drawCircle(radius, radius, radius, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        canvas.drawBitmap(picture, rect, rect, paint);
        picture = null;
        return resultBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable() ;
        //如果图片为空，直接放返回
        if (drawable == null)
            return;
        //如果控件的宽高其中一个为0，直接返回
        if (getWidth() == 0 || getHeight() == 0)
            return;
        this.measure(0, 0);
        //如果是.9图也无法显示，直接返回
        if(drawable.getClass() == NinePatchDrawable.class)
            return;
        Bitmap b = null ;
        try{
            b = ((BitmapDrawable) drawable).getBitmap();
        }
        catch (ClassCastException e){
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
        int radius = (defaultHeight > defaultWidth ? defaultWidth :defaultHeight )/2;
        Bitmap roundBitmap = handlePicture(bitmap, radius);
        canvas.drawBitmap(roundBitmap,0,0,null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setFilterBitmap(true);//对位图进行滤波处理
        paint.setDither(true);//设置防抖动
        paint.setColor(color);
        paint.setStrokeWidth(width);
        if (style==1){
            Path path = new Path();
            path.moveTo(radius,0);
            path.quadTo(0,0,0,radius);
            path.quadTo(0,2*radius,radius,2*radius);
            path.quadTo(2*radius,2*radius,2*radius,radius);
            path.quadTo(2*radius,0,radius,0);
            canvas.drawPath(path,paint);
        }
        else if (style==0){
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(0,2*radius);
            path.lineTo(2*radius,2*radius);
            path.lineTo(2*radius,0);
            path.lineTo(0,0);
            canvas.drawPath(path,paint);
        }

    }

    /**
     * Convert a drawable object into a Bitmap.
     *
     * @param drawable Drawable to extract a Bitmap from.
     * @return A Bitmap created from the drawable parameter.
     */
    protected Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null)   // Don't do anything without a proper drawable
            return null;
        else if (drawable instanceof BitmapDrawable) {  // Use the getBitmap() method instead if BitmapDrawable
            ((BitmapDrawable) drawable).getBitmap();
        }

        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        if (!(intrinsicWidth > 0 && intrinsicHeight > 0))
            return null;

        try {
            // Create Bitmap object out of the drawable
            Bitmap bitmap = bitmapPool.get(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Simply return null of failed bitmap creations
            return null;
        }
    }
}
