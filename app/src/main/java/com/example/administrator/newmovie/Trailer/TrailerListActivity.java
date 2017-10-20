package com.example.administrator.newmovie.Trailer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.CustomView.TitleBar;
import com.trello.rxlifecycle.components.RxActivity;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrailerListActivity extends RxActivity {

    private int movieId ;
    private String movieName ;
    private List<TrailerData.VideoListBean> mTrailerData ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout ;
    private TrailerRecyclerView trailerList ;
    private TitleBar mTitleBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_list);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        trailerList = (TrailerRecyclerView) findViewById(R.id.trailer_list);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.trailer_refreshlayout);
        Intent intent  = getIntent();
        movieId = intent.getIntExtra("MOVIEID",1);
        movieName = intent.getStringExtra("MOVIENAME");
        mTitleBar.setTitle(movieName);
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
                getTrailerByLocationIdAndMovieId(1, movieId);
            }
        });
        getTrailerByLocationIdAndMovieId(1, movieId);
    }

    private void getTrailerByLocationIdAndMovieId(int pageindex , final int movieid) {
        MovieManager.INSTANCE()
                .getTrailerByPageIndexAndMovieId(pageindex,movieid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrailerData>() {
                    @Override
                    public void call(TrailerData trailerData) {
                        trailerList.bindData(trailerData.getVideoList());
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getTrailerByLocationIdAndMovieId(1,movieid);
                        Log.e("MAIN",throwable.toString());
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
}
