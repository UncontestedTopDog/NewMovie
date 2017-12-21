package com.example.administrator.newmovie.Homework;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Command.HideImageCommand;
import com.example.administrator.newmovie.CustomView.CircularImageView;
import com.example.administrator.newmovie.Data.TouTiaoVideoData;
import com.example.administrator.newmovie.Data.XiGuaMovieData;
import com.example.administrator.newmovie.NewJZVideoPlayerStandard;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.RxBus;
import com.example.administrator.newmovie.TodayNewsVideo.Video;
import com.example.administrator.newmovie.TodayNewsVideo.VideoPathDecoder;

import cn.jzvd.JZVideoPlayer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by Administrator on 2017/9/23.
 */

public class VideoListCardV2 extends RelativeLayout {
    private static final String TAG = "VideoListCard";
    private NewJZVideoPlayerStandard mVideoPlayer ;
    private CircularImageView mAvatarImage ;
    private TextView mAvatarName ;
    private Subscription subscription;

    public VideoListCardV2(Context context) {
        super(context);
        initView();
    }

    public VideoListCardV2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VideoListCardV2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.video_list_card,this);
        mVideoPlayer = (NewJZVideoPlayerStandard) findViewById(R.id.video_player);
        mAvatarImage = (CircularImageView) findViewById(R.id.avatar_image);
        mAvatarName = (TextView) findViewById(R.id.avatar_name);
    }

//    public boolean bindData(final XiGuaMovieData mXiGuaMovieData){
//        if (mXiGuaMovieData == null)
//            return false ;
//        if (mXiGuaMovieData.getPlayer_url() == null)
//            parseUrl(mXiGuaMovieData);
////            parseUrl(mXiGuaMovieData.getShare_url(),mXiGuaMovieData.getVideo_duration_str(),mXiGuaMovieData.getTitle(),mXiGuaMovieData.getMedia_creator_id());
//        Glide.with(getContext())
//                .load(mXiGuaMovieData.getImage_url())
//                .centerCrop()
//                .placeholder(R.drawable.no_pictrue)
//                .error(R.drawable.download_fail_hint)
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(mVideoPlayer.thumbImageView) {
//                          @Override
//                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                              super.onResourceReady(drawable, anim);
//                          }
//                      }
//                );
//        Glide.with(getContext())
//                .load(mXiGuaMovieData.getMedia_avatar_url())
//                .centerCrop()
//                .placeholder(R.drawable.no_pictrue)
//                .error(R.drawable.download_fail_hint)
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(mAvatarImage) {
//                          @Override
//                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                              super.onResourceReady(drawable, anim);
//                          }
//                      }
//                );
//        mAvatarName.setText(mXiGuaMovieData.getMedia_name());
//        subscription = RxBus.getDefault().register(HideImageCommand.class, this)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<HideImageCommand>() {
//                    @Override
//                    public void call(HideImageCommand cmd) {
//                        if (cmd.getMedia_creator_id() ==  mXiGuaMovieData.getMedia_creator_id())
//                            mAvatarImage.setVisibility(cmd.isShow()?VISIBLE:INVISIBLE);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                });
//        return true ;
//    }

    public boolean bindData(final TouTiaoVideoData mTouTiaoVideoData){
        if (mTouTiaoVideoData == null)
            return false ;
        if (mTouTiaoVideoData.getPlayer_url() == null)
            parseUrl(mTouTiaoVideoData);
//            parseUrl(mXiGuaMovieData.getShare_url(),mXiGuaMovieData.getVideo_duration_str(),mXiGuaMovieData.getTitle(),mXiGuaMovieData.getMedia_creator_id());
        Glide.with(getContext())
                .load(mTouTiaoVideoData.getImage_url())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(mVideoPlayer.thumbImageView) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        Glide.with(getContext())
                .load(mTouTiaoVideoData.getMedia_avatar_url())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(mAvatarImage) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        mAvatarName.setText(mTouTiaoVideoData.getSource());
        subscription = RxBus.getDefault().register(HideImageCommand.class, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HideImageCommand>() {
                    @Override
                    public void call(HideImageCommand cmd) {
                        if (cmd.getMedia_creator_id() ==  mTouTiaoVideoData.getGroup_id())
                            mAvatarImage.setVisibility(cmd.isShow()?VISIBLE:INVISIBLE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        return true ;
    }

    private void parseUrl(String url, final String time, final String title, final long mediaCreatorId) {
        //解析地址
        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video s) {
                Log.i(TAG,s.toString());
                mVideoPlayer.setUp(s.main_url, JZVideoPlayer.SCREEN_WINDOW_NORMAL, "");

            }

            @Override
            public void onDecodeError(Throwable e) {
            }
        };
        Log.i(TAG,url);
        decoder.decodePath(url);
    }

    private void parseUrl(final XiGuaMovieData mXiGuaMovieData) {
        //解析地址
        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video s) {
                Log.i(TAG,s.toString());
                mVideoPlayer.setUp(s.main_url, JZVideoPlayer.SCREEN_WINDOW_NORMAL, "");
                mXiGuaMovieData.setPlayer_url(s.main_url);
            }

            @Override
            public void onDecodeError(Throwable e) {
            }
        };
        Log.i(TAG,mXiGuaMovieData.getShare_url());
        decoder.decodePath(mXiGuaMovieData.getShare_url());
    }

    private void parseUrl(final TouTiaoVideoData mTouTiaoVideoData) {
        //解析地址
        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video s) {
                Log.i(TAG,s.toString());
                mVideoPlayer.setUp(s.main_url, JZVideoPlayer.SCREEN_WINDOW_NORMAL, "");
                mTouTiaoVideoData.setPlayer_url(s.main_url);
            }

            @Override
            public void onDecodeError(Throwable e) {
            }
        };
        Log.i(TAG,mTouTiaoVideoData.getShare_url());
        decoder.decodePath(mTouTiaoVideoData.getShare_url());
    }
}
