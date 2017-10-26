package com.example.administrator.newmovie.DirectorAndActors;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.MovieDetail;
import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/10/20.
 */

public class DirectorAndActorCard extends LinearLayout {
    private ImageView img ;
    private TextView name ;
    private TextView nameen ;
    private TextView rolename ;
    public DirectorAndActorCard(Context context) {
        super(context);
        initView();
    }

    public DirectorAndActorCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DirectorAndActorCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.direcotr_and_actor_card,this);
        img = (ImageView) findViewById(R.id.img);
        name = (TextView) findViewById(R.id.name);
        nameen = (TextView) findViewById(R.id.nameen);
        rolename = (TextView) findViewById(R.id.rolename);
    }

    public void bindData(MovieDetail.DataBean.BasicBean.DirectorBean directorBean) {
//        Glide.with(getContext()).load(directorBean.getImg()).into(img);
        Glide.with(getContext())
                .load(directorBean.getImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(img) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        name.setText(directorBean.getName());
        nameen.setText(directorBean.getNameEn());
        rolename.setText("导演");
    }
    public void bindData(MovieDetail.DataBean.BasicBean.ActorsBean actorsBean) {
//        Glide.with(getContext()).load(actorsBean.getImg()).into(img);
        Glide.with(getContext())
                .load(actorsBean.getImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(img) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        name.setText(actorsBean.getName());
        nameen.setText(actorsBean.getNameEn());
        rolename.setText("饰："+actorsBean.getRoleName());
    }

}
