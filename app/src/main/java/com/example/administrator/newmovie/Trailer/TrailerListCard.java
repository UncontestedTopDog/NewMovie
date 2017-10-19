package com.example.administrator.newmovie.Trailer;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.newmovie.MyVideoPlayerStandard;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.RoundImageView;

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
        Glide.with(getContext()).load(trailerData.getImage()).into(trailerPlayer.thumbImageView);
        Glide.with(getContext()).load(trailerData.getImage()).into(mRoundImageView);
        return true ;
    }
}
