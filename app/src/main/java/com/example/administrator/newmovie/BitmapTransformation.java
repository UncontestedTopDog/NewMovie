package com.example.administrator.newmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Created by ruoshili on 7/15/15.
 */
public class BitmapTransformation implements Transformation<Bitmap> {

    private static final String TAG = "BitmapTransformation";

    public final static int UnkType = 0;
    public final static int CircleType = 1;
    public final static int CornerRadiusType = 2;

    private final int targetDensity;
    private final int type;
    private final BitmapPool bitmapPool;
    private final int backgroundColor;

    private final int cornerRadius;
    private final String id;
    public BitmapTransformation(final String id, Context context) {
        this(id,context, Color.TRANSPARENT,CircleType,0);

    }

    public BitmapTransformation(final String id, Context context, int cornerRadius) {
        this(id,context, Color.TRANSPARENT, CornerRadiusType, cornerRadius);

    }

    private BitmapTransformation(final String id, Context context, int backgroundColor, int type, int cornerRadius) {
        this.targetDensity = DimensionUtil.targetDensity(context);
        this.bitmapPool = Glide.get(context).getBitmapPool();
        this.backgroundColor = backgroundColor;
        this.type = type;
        this.cornerRadius = cornerRadius;
        this.id = id;
        Log.i(TAG,"cotor, id: " + id + ", type: " + type + ",cornerRadius: " + cornerRadius + ",targetDensity: " + targetDensity);
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

        Log.i(TAG,"transform,type: " + type);

        Drawable drawable = null;
        if (type == CircleType) {
            drawable = new CircleDrawable(source, null, 0, targetDensity);
        } else if (type == CornerRadiusType) {
            drawable = new RoundedDrawable(source, cornerRadius, 0, targetDensity);
        }

        if (drawable == null) {
            Log.e(TAG,"transform, drawable is null");
            return null;
        }

        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            Log.e(TAG,"transform, width is zero");
            return null;
        }
        drawable.setBounds(0,0,intrinsicWidth,intrinsicHeight);

        Bitmap bitmap = bitmapPool.get(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        }
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroundColor);

        drawable.draw(canvas);

        return BitmapResource.obtain(bitmap, bitmapPool);
    }

    @Override
    public String getId() {
        return id+"(type" + type + ")";
    }
}
