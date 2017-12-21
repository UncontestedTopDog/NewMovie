package com.example.administrator.newmovie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.newmovie.Command.HideImageCommand;
import com.example.administrator.newmovie.Command.RotateScreenCommand;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2017/12/20.
 */

public class NewJZVideoPlayerStandard extends JZVideoPlayerStandard {

    private TextView mTimeLength ;

    private String mClassName ;

    private String mMediaCreatorId = "1" ;

    public NewJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public NewJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public String getmMediaCreatorId() {
        return mMediaCreatorId;
    }

    public void setmMediaCreatorId(String mMediaCreatorId) {
        this.mMediaCreatorId = mMediaCreatorId;
    }

    @Override
    public int getLayoutId() {
        return R.layout.new_jz_layout_standard;
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        if (objects.length>1)
        mTimeLength.setText(objects[1]+"");
    }

    //    1. init函数——这是播放控件初始化的时候最先调用的

    @Override
    public void init(Context context) {
        super.init(context);
        mTimeLength = (TextView) findViewById(R.id.time_length);
    }

//    2. onClick函数——这是控件里所有控件的onClick响应函数，比如监听开始按钮的点击，全屏按钮的点击，空白的点击，retry按钮的点击，等。如果你想拦截这些点击的响应或者继承这些点击的响应，那么复写此函数

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.start){
            HideImageCommand hideImageCommand = new HideImageCommand(false,mMediaCreatorId);
            RxBus.getDefault().post(hideImageCommand);
        }
    }

//    3. onTouch函数——在JCVideoPlayer中此函数主要响应了全屏之后的手势控制音量和进度

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

//    4. startVideo函数——用户触发的视频开始播放

    @Override
    public void startVideo() {
        super.startVideo();
        mTimeLength.setVisibility(GONE);
    }

//    5. onVideoRendingStart函数——用户触发的视频开始播放后进入preparing状态，当视频准备完毕之后进入onVideoRendingStart函数，开始播放

//    6. onStateNormal函数——控件进入普通的未播放状态

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        mTimeLength.setVisibility(VISIBLE);
    }

//    7. onStatePreparing函数——进入preparing状态，正在初始化视频

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        mTimeLength.setVisibility(GONE);
    }


//    8. onStatePlaying函数——preparing之后进入播放状态

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        mTimeLength.setVisibility(GONE);
    }


//    9. onStatePause函数——暂停视频，进入暂停状态

    @Override
    public void onStatePause() {
        super.onStatePause();
        mTimeLength.setVisibility(GONE);
    }


//    10. onStatePlaybackBufferingStart函数——在播放状态下seek进度，进入缓存视频的状态

//    11. onStateError函数——进入错误状态

    @Override
    public void onStateError() {
        super.onStateError();
        mTimeLength.setVisibility(GONE);
    }


//    12. onStateAutoComplete函数——进入视频自动播放完成状态

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        mTimeLength.setVisibility(VISIBLE);
    }


//    13. onInfo函数——android.media.MediaPlayer回调的info

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }


//    14. onError函数——android.media.MediaPlayer回调的error

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }


//    15. startWindowFullscreen函数——进入全屏

    @Override
    public void startWindowFullscreen() {
        RotateScreenCommand rotateScreenCommand = new RotateScreenCommand(true,mClassName);
        RxBus.getDefault().post(rotateScreenCommand);
        super.startWindowFullscreen();
        mTimeLength.setVisibility(GONE);
    }

//    16. startWindowTiny函数—— 退出全屏

    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
        RotateScreenCommand rotateScreenCommand = new RotateScreenCommand(false,mClassName);
        RxBus.getDefault().post(rotateScreenCommand);
    }
}
