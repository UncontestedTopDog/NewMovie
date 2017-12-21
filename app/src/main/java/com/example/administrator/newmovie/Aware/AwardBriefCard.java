package com.example.administrator.newmovie.Aware;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.administrator.newmovie.Data.MovieAward;
import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AwardBriefCard extends LinearLayout {
    private static final String TAG = "ImageTypeBriefCard";
    private AwardRecyclerView awardRecyclerView ;
    private int screenHeight ;
    private MovieAward movieAward ;

    public AwardBriefCard(Context context) {
        super(context);
        initView();
    }

    public AwardBriefCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AwardBriefCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.award_brief_card, this);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenHeight= wm.getDefaultDisplay().getWidth();
    }

    public void bindData(MovieAward movieAward) {
        if (movieAward == null) {
            Log.d(TAG, "bind data error user = null");
            return;
        }
        awardRecyclerView = (AwardRecyclerView) findViewById(R.id.award_list);
        awardRecyclerView.bindData(movieAward);
    }

    public void startShowAnim(final Animation.AnimationListener listener) {
        setVisibility(VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,-screenHeight,0);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(200);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (listener != null) {
                    listener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (listener != null) {
                    listener.onAnimationRepeat(animation);
                }
            }
        });
        startAnimation(set);
    }

    /**
     * @param listener 不需要额外处理，就传null
     */
    public void startHideAnim(final Animation.AnimationListener listener) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-screenHeight);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(200);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (listener != null) {
                    listener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
                if (listener != null) {
                    listener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (listener != null) {
                    listener.onAnimationRepeat(animation);
                }
            }
        });
        startAnimation(set);
    }

}

