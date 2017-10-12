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
 * Created by chenjh on 2015/9/24.
 * Desc:
 */
public class RoundedDrawable extends Drawable {

    private static final String TAG = "RoundedDrawable";

    private int density = DisplayMetrics.DENSITY_DEFAULT;
    private int bitmapWidth;
    private int bitmapHeight;

    protected final float cornerRadius;
    protected final int margin;
    protected final Bitmap bitmap;
    protected final RectF rectRound = new RectF(), bitmapRect;
    protected final BitmapShader shader;
    protected final Paint paint;

    public RoundedDrawable(Bitmap bitmap, float cornerRadius, int margin, int density) {

        Log.i(TAG,"cotor");

        this.cornerRadius = cornerRadius;
        this.margin = margin;
        this.bitmap = bitmap;
        this.shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.bitmapRect = new RectF(margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setShader(shader);
        this.paint.setFilterBitmap(true); //用来抗锯齿的
        this.paint.setDither(true);
        this.density = density;

        computeBitmapSize();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        Log.i(TAG,"onBoundsChange: " + bounds);

        rectRound.set(margin, margin, bounds.width() - margin, bounds.height() - margin);

        // Resize the original bitmap to fit the new bound
        Matrix shaderMatrix = new Matrix();
        shaderMatrix.setRectToRect(bitmapRect, rectRound, Matrix.ScaleToFit.FILL);
        shader.setLocalMatrix(shaderMatrix);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(rectRound, cornerRadius, cornerRadius, paint);
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
