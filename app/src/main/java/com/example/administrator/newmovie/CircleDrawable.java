package com.example.administrator.newmovie;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by huqiuyun on 16/10/10.
 */

public class CircleDrawable extends Drawable {

    private static final String TAG = "RoundedDrawable";

    private int density = DisplayMetrics.DENSITY_DEFAULT;
    private int bitmapWidth;
    private int bitmapHeight;

    protected float radius;
    protected final RectF rectRound = new RectF();
    protected final RectF bitmapRect;
    protected final Bitmap bitmap;
    protected final BitmapShader bitmapShader;
    protected final Paint paint;
    protected final Paint strokePaint;
    protected final float strokeWidth;
    protected float strokeRadius;

    public CircleDrawable(Bitmap bitmap, Integer strokeColor, float strokeWidth, int density) {

        this.radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2;
        this.bitmap = bitmap;
        this.bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.bitmapRect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setShader(this.bitmapShader);
        this.paint.setFilterBitmap(true); //用来抗锯齿的
        this.paint.setDither(true);

        if (strokeColor == null) {
            this.strokePaint = null;
        } else {
            this.strokePaint = new Paint();
            this.strokePaint.setStyle(Paint.Style.STROKE);
            this.strokePaint.setColor(strokeColor);
            this.strokePaint.setStrokeWidth(strokeWidth);
            this.strokePaint.setAntiAlias(true);
        }
        this.strokeWidth = strokeWidth;
        this.strokeRadius = this.radius - strokeWidth / 2;
        this.density = density;

        computeBitmapSize();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        rectRound.set(0, 0, bounds.width(), bounds.height());
        radius = Math.min(bounds.width(), bounds.height()) / 2;
        strokeRadius = radius - strokeWidth / 2;

        // Resize the original bitmap to fit the new bound
        Matrix shaderMatrix = new Matrix();
        shaderMatrix.setRectToRect(bitmapRect, rectRound, Matrix.ScaleToFit.FILL);
        bitmapShader.setLocalMatrix(shaderMatrix);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(radius, radius, radius, paint);
        if (strokePaint != null) {
            canvas.drawCircle(radius, radius, strokeRadius, strokePaint);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getIntrinsicWidth() {
        return this.bitmapWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return this.bitmapHeight;
    }

    private void computeBitmapSize() {
        final Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            bitmapWidth = bitmap.getScaledWidth(density);
            bitmapHeight = bitmap.getScaledHeight(density);
        } else {
            bitmapWidth = bitmapHeight = -1;
        }
        Log.i(TAG,"bitmapWidth: " + bitmapWidth + ",bitmapHeight: " + bitmapHeight);
    }
}
