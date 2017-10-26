package com.example.administrator.newmovie.Trailer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.CustomView.MyVideoPlayerStandard;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.CustomView.RoundImageView;

import cn.jzvd.JZVideoPlayer;


/**
 * Created by Administrator on 2017/9/23.
 */

public class TrailerListCard extends RelativeLayout {
    private MyVideoPlayerStandard trailerPlayer ;
    private TextView trailerTitle ;
    private RoundImageView mRoundImageView ;
    private ImageButton like ;
    private ImageButton share ;
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

    private void initView() {
        inflate(getContext(), R.layout.trailer_list_card,this);
        trailerPlayer = (MyVideoPlayerStandard) findViewById(R.id.trailer_player);
        trailerTitle = (TextView) findViewById(R.id.trailer_title);
        mRoundImageView = (RoundImageView) findViewById(R.id.image);
        like = (ImageButton) findViewById(R.id.like);
        share = (ImageButton) findViewById(R.id.share);
    }

    public boolean bindData(TrailerData.VideoListBean trailerData){
        if (trailerData == null)
            return false ;
//        String url = trailerData.getHightUrl().replaceAll("https","http") ;

        String s1 = trailerData.getLength() / 60 < 10 ? "0" + trailerData.getLength() / 60 : trailerData.getLength() / 60 + "";
        String s2 = trailerData.getLength() % 60 < 10 ? "0"+ trailerData.getLength() % 60 :trailerData.getLength() % 60 + "";
        String timelength = s1 + ":" + s2;

        trailerPlayer.setUp(trailerData.getHightUrl(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "",timelength);
        trailerTitle.setText(trailerData.getTitle());
//        Glide.with(getContext()).load(trailerData.getImage()).into(trailerPlayer.thumbImageView);
        Glide.with(getContext()).load(trailerData.getImage()).into(mRoundImageView);

        Glide.with(getContext())
                .load(trailerData.getImage())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(trailerPlayer.thumbImageView) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );

//        Glide.with(getContext())
//                .load(trailerData.getImage())
//                .centerCrop()
//                .placeholder(R.drawable.no_pictrue)
//                .error(R.drawable.download_fail_hint)
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(mRoundImageView) {
//                          @Override
//                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                              super.onResourceReady(drawable, anim);
//                          }
//                      }
//                );

        return true ;
    }
}
