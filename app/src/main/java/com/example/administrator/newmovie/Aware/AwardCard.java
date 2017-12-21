package com.example.administrator.newmovie.Aware;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.MovieAward;
import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AwardCard extends RelativeLayout {
    private ImageView awardImg ;
    private TextView winCount ;
    private TextView nominationCount ;
    private LinearLayout awardLayout  ;
    private AwardDeatilRecyclerView awardDeatilRecyclerView ;
    public AwardCard(Context context) {
        super(context);
        initView();
    }

    public AwardCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AwardCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.award_list,this);
        awardImg = (ImageView) findViewById(R.id.award_img);
        winCount = (TextView) findViewById(R.id.win_award);
        nominationCount = (TextView) findViewById(R.id.nomination_award);
        awardLayout = (LinearLayout) findViewById(R.id.award_layout);
        awardDeatilRecyclerView = (AwardDeatilRecyclerView) findViewById(R.id.award_detail);
        awardImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awardLayout.getVisibility()==VISIBLE){
                    awardLayout.setVisibility(GONE);
                    awardDeatilRecyclerView.setVisibility(VISIBLE);
                }else {
                    awardLayout.setVisibility(VISIBLE);
                    awardDeatilRecyclerView.setVisibility(GONE);
                }
            }
        });
    }

    public void bindData(MovieAward movieAward, int position){
        Glide.with(getContext())
                .load(movieAward.getFestivals().get(position).getFestivalImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(awardImg) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        winCount.setText("获奖次数:"+movieAward.getAwards().get(position).getWinCount());
        nominationCount.setText("提名次数:"+movieAward.getAwards().get(position).getNominateCount());
        awardDeatilRecyclerView.bindData(movieAward.getAwards().get(position));
    }


}
