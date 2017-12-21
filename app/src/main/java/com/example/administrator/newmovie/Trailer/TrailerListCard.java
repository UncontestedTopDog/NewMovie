package com.example.administrator.newmovie.Trailer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.Data.XiGuaMovieData;
import com.example.administrator.newmovie.NewJZVideoPlayerStandard;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.CustomView.RoundImageView;
import com.example.administrator.newmovie.TodayNewsVideo.Video;
import com.example.administrator.newmovie.TodayNewsVideo.VideoPathDecoder;
import com.example.administrator.newmovie.Utils.TimeUtil;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


/**
 * Created by Administrator on 2017/9/23.
 */

public class TrailerListCard extends RelativeLayout {
    private static final String TAG = "TrailerListCard";
    private String mClassName ;
    private NewJZVideoPlayerStandard mTrailerPlayer ;
    private TextView mTrailerTitle ;
    private RoundImageView mRoundImageView ;
    private ImageButton mLike ;
    private ImageButton mFollow ;
    public TrailerListCard(Context context) {
        super(context);
        initView();
    }

    public TrailerListCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TrailerListCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    private void initView() {
        inflate(getContext(), R.layout.trailer_list_card,this);
        mTrailerPlayer = (NewJZVideoPlayerStandard) findViewById(R.id.trailer_player);
        mTrailerPlayer.setmClassName(mClassName);
        mTrailerTitle = (TextView) findViewById(R.id.trailer_title);
        mRoundImageView = (RoundImageView) findViewById(R.id.image);
        mLike = (ImageButton) findViewById(R.id.like);
        mFollow = (ImageButton) findViewById(R.id.follow);
    }

    public boolean bindData(TrailerData.VideoListBean trailerData){
        if (trailerData == null)
            return false ;

        String timelength = TimeUtil.secondToTime(trailerData.getLength()) ;

        mTrailerPlayer.setUp(trailerData.getHightUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, trailerData.getTitle(), timelength);
        mTrailerTitle.setText(trailerData.getTitle());

        Glide.with(getContext()).load(trailerData.getImage()).into(mRoundImageView);

        Glide.with(getContext())
                .load(trailerData.getImage())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(mTrailerPlayer.thumbImageView) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );

        return true ;
    }

    public boolean bindData(XiGuaMovieData mXiGuaMovieData){
        if (mXiGuaMovieData == null)
            return false ;
        parseUrl(mXiGuaMovieData.getShare_url(),mXiGuaMovieData.getVideo_duration_str(),mXiGuaMovieData.getTitle());

        mTrailerTitle.setText(mXiGuaMovieData.getMedia_name());

        Glide.with(getContext()).load(mXiGuaMovieData.getMedia_avatar_url()).into(mRoundImageView);

        Glide.with(getContext())
                .load(mXiGuaMovieData.getImage_url())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(mTrailerPlayer.thumbImageView) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );

        return true ;
    }

    private void parseUrl(String url, final String time , final String title) {
        //解析地址
        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video s) {
                Log.i(TAG,s.toString());
                mTrailerPlayer.setUp(s.main_url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title, time);
            }

            @Override
            public void onDecodeError(Throwable e) {
            }
        };
        Log.i(TAG,url);
        decoder.decodePath(url);
    }
}
