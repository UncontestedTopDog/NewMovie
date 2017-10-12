package com.example.administrator.newmovie;

import android.graphics.Point;
import android.view.View;

import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * 优化Slide缓存图像
 * 图片宽度大于屏幕尺寸80%时，使用统一的缓存Size，提高了各种大图页打开的速度，也避免了多份缓存
 *
 * Created by caijw on 2015/7/26.
 */
public abstract class SmartViewTarget<T extends View, Z> extends ViewTarget<T, Z> {
    int screenShortSideLen;

    public SmartViewTarget(T view) {
        super(view);

        Point screenSize = new Point();
        DimensionUtil.getScreenSize(view.getContext(), screenSize);
        screenShortSideLen = Math.min(screenSize.x, screenSize.y);
    }

    @Override
    public void getSize(final SizeReadyCallback cb) {
        super.getSize(new SizeReadyCallback() {
            @Override
            public void onSizeReady(int width, int height) {
                int smartSideLen = Math.min(width, height);
                if (smartSideLen != screenShortSideLen && smartSideLen * 10 > screenShortSideLen * 8) {
                    smartSideLen = screenShortSideLen;
                }

                cb.onSizeReady(smartSideLen, smartSideLen);
            }
        });
    }
}
