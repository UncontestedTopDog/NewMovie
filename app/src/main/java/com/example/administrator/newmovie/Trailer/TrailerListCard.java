package com.example.administrator.newmovie.Trailer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.newmovie.MyVideoPlayerStandard;
import com.example.administrator.newmovie.R;

import cn.jzvd.JZVideoPlayer;


/**
 * Created by Administrator on 2017/9/23.
 */

public class TrailerListCard extends RelativeLayout {
    private MyVideoPlayerStandard trailerPlayer ;
    private TextView trailerTitle ;
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
        trailerTitle = (TextView) findViewById(R.id.title);
    }

    public boolean bindData(TrailerData.VideoListBean trailerData){
        if (trailerData == null)
             return false ;
        String url = trailerData.getHightUrl().replaceAll("https","http") ;

        String timelength = trailerData.getLength()/60+":"+trailerData.getLength()%60;

        trailerPlayer.setUp(url, JZVideoPlayer.SCREEN_LAYOUT_LIST, trailerData.getTitle(),timelength);
        Glide.with(getContext()).load(trailerData.getImage()).into(trailerPlayer.thumbImageView);
        return true ;
    }
}
