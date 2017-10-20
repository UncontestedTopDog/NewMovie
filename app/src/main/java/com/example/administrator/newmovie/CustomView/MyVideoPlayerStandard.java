package com.example.administrator.newmovie.CustomView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.newmovie.R;

import cn.jzvd.JZUserAction;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayerStandard;


/**
 * Created by Administrator on 2017/10/10.
 */

public class MyVideoPlayerStandard extends JZVideoPlayerStandard {

    private TextView timeLength ;

    public MyVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        timeLength = (TextView) findViewById(R.id.time_length);
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        timeLength.setText(objects[1].toString());
    }

    @Override
    public int getLayoutId() {
        return R.layout.myvideoplayerstandard;
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        timeLength.setVisibility(VISIBLE);
        topContainer.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
//        int i = v.getId();
//        if (i == cn.jzvd.R.id.fullscreen) {
//            if (!(currentScreen == SCREEN_WINDOW_FULLSCREEN)) {
//                JZUtils.setRequestedOrientation(getContext(), ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            }
//        }
        super.onClick(v);
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        timeLength.setVisibility(INVISIBLE);
        topContainer.setVisibility(INVISIBLE);
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        timeLength.setVisibility(VISIBLE);
        topContainer.setVisibility(VISIBLE);
    }

    @Override
    public void onVideoRendingStart() {
        super.onVideoRendingStart();
        topContainer.setVisibility(INVISIBLE);
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        timeLength.setVisibility(INVISIBLE);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
        timeLength.setVisibility(INVISIBLE);
    }

    @Override
    public void autoFullscreen(float x) {
//        super.autoFullscreen(x);
    }
}
