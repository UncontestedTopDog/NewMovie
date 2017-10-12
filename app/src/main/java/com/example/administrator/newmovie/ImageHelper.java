package com.example.administrator.newmovie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

import java.io.File;
import java.io.IOException;

/**
 * Created by ruoshili on 12/31/14.
 */
public final class ImageHelper {
    private static final String TAG = "ImageHelper";

    public static final int kImageType = R.id.imageTypeKey;
    public static final int kImageLoaded = R.id.imageLoadedKey;
    public static final Long ImgRes = new Long(1);
    public static final Long ImgUrl = new Long(2);
    public static final Long ImgNull= new Long(3);

    private ImageHelper() {
    }

    public static Bitmap extractThumbnail(File imgFile, int dstWidth, int dstHeight) {
        if (!imgFile.exists()) {
            return null;
        }
        Bitmap b = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        if (b == null) {
            return null;
        }
        Bitmap ret = ThumbnailUtils.extractThumbnail(b, dstWidth, dstHeight);
        b.recycle();
        return ret;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static void loadImageIntoImageView(@NonNull final Context context,
                                              final String url,
                                              @NonNull final ImageView imageView) {
        loadImageIntoImageView(context, url, imageView, 0);
    }

    public static void loadImageIntoImageView(@NonNull final Context context,
                                              final String url,
                                              final boolean cropCircle,
                                              @NonNull final ImageView imageView,
                                              final int placeHolder) {
        loadImageIntoImageView(context, url, cropCircle, imageView, placeHolder, cropCircle);
    }

    public static void loadImageIntoImageView(final Context context,
                                              final String url,
                                              final boolean cropCircle,
                                              final ImageView imageView,
                                              final int placeHolder,
                                              final boolean cropCirclePlaceholder) {
        if (context == null) {
            return;
        }

        if (context instanceof Activity && ((Activity)context).isFinishing()){
            return;
        }

        if (imageView == null) {
            return;
        }

        if (TextUtils.isEmpty(url)) {
            if (cropCirclePlaceholder) {
                Glide.with(context)
                        .load(placeHolder)
                        .bitmapTransform(new BitmapTransformation("",context))
                        .into(imageView);
            } else {
                imageView.setImageResource(placeHolder);
            }
            imageView.setTag(kImageType,ImgRes);
            imageView.setTag(kImageLoaded,ImgRes);
            return;
        }

        imageView.setTag(kImageType,ImgUrl);
        final DrawableRequestBuilder builder = Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        if (placeHolder != 0) {
            builder.placeholder(placeHolder).dontAnimate();
        }

        if (cropCircle) {
            builder.bitmapTransform(new BitmapTransformation(StringUtils.stringToMd5(url),context));
        }

        builder.into(new SmartViewTarget<ImageView, GlideDrawable>(imageView) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
                imageView.setImageDrawable(placeholder);
                imageView.setTag(kImageLoaded,ImgRes);
            }

            @Override
            public void onResourceReady(GlideDrawable resource,
                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
                imageView.setTag(kImageLoaded, ImgUrl);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                imageView.setImageResource(placeHolder);
                imageView.setTag(kImageLoaded,ImgRes);
            }
        });
    }


    public static void loadImageIntoImageView(@NonNull final Context context,
                                              final String url,
                                              @NonNull final ImageView imageView,
                                              final int placeHolder) {
        loadImageIntoImageView(context, url, false, imageView, placeHolder);

    }


    /**
     * 旋转图片(目前支持正确的选装90度单位)
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotateImageView(int angle, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        // 旋转图片 动作 
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();

        //    创建新的图片 
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    public static Drawable getRoundedDrawable(@NonNull Context context, int imgResId, float cornerRadius) {
        Drawable drawable = context.getResources().getDrawable(imgResId);
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            throw new IllegalArgumentException("arg imgResId must be a bitmap res id");
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        RoundedDrawable roundedDrawable = RoundedDrawableFactory.create(context, bitmap, cornerRadius, 0);
        return roundedDrawable;
    }
}
