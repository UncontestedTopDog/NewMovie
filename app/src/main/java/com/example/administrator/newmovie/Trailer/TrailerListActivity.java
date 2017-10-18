package com.example.administrator.newmovie.Trailer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.MovieManager;
import com.example.administrator.newmovie.R;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrailerListActivity extends RxFragmentActivity {

    private int movieid ;
    private List<TrailerData.VideoListBean> mTrailerData ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout ;
    private TrailerRecyclerView trailerList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_list);
        trailerList = (TrailerRecyclerView) findViewById(R.id.trailer_list);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.trailer_refreshlayout);
        Intent intent  = getIntent();
        movieid = intent.getIntExtra("MOVIEID",1);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTrailerByPageIndexAndMovieId(1, movieid);
            }
        });
        getTrailerByPageIndexAndMovieId(1, movieid);
    }

    private void getTrailerByPageIndexAndMovieId(int pageindex , final int movieid) {
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
                        getTrailerByPageIndexAndMovieId(1,movieid);
                        Log.e("MAIN",throwable.toString());
                    }
                });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
