package com.example.administrator.newmovie.Trailer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.BaseActivity;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.CustomView.TitleBar;
import com.example.administrator.newmovie.Command.RotateScreenCommand;
import com.example.administrator.newmovie.RxBus;

import cn.jzvd.JZVideoPlayer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrailerListActivity extends BaseActivity {

    private int mMovieId;
    private String mMovieName;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout;
    private TrailerRecyclerView mTrailerList;
    private TitleBar mTitleBar;
    private Subscription mSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_list);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTrailerList = (TrailerRecyclerView) findViewById(R.id.trailer_list);
        mTrailerList.setmClassName(getClass().getName());
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.trailer_refreshlayout);
        Intent intent = getIntent();
        mMovieId = intent.getIntExtra("MOVIEID", 1);
        mMovieName = intent.getStringExtra("MOVIENAME");
        mTitleBar.setTitle(mMovieName);
        mTitleBar.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JZVideoPlayer.backPress()) {
                    return;
                }
                finish();
            }
        });
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTrailerByLocationIdAndMovieId(1, mMovieId);
            }
        });
        getTrailerByLocationIdAndMovieId(1, mMovieId);
        mSubscription = RxBus.getDefault().register(RotateScreenCommand.class, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RotateScreenCommand>() {
                    @Override
                    public void call(RotateScreenCommand cmd) {
                        if (cmd.getClassname().equals(getClass().getName())) {
                            if (cmd.isB()) {
                                //横屏设置
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            } else {
                                //竖屏设置
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void getTrailerByLocationIdAndMovieId(int pageindex, final int movieid) {
        MovieManager.INSTANCE()
                .getTrailerByPageIndexAndMovieId(pageindex, movieid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrailerData>() {
                    @Override
                    public void call(TrailerData trailerData) {
                        mTrailerList.bindData(trailerData.getVideoList());
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getTrailerByLocationIdAndMovieId(1, movieid);
                        Log.e("MAIN", throwable.toString());
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
