package com.example.administrator.newmovie.Trailer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.MovieManager;
import com.example.administrator.newmovie.R;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrailerListActivity extends AppCompatActivity {

    private int movieid ;
    private List<TrailerData.VideoListBean> mTrailerData ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout ;
    private TrailerRecyclerView trailerList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trailer_list);
        trailerList = (TrailerRecyclerView) findViewById(R.id.trailer_list);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.trailer_refreshlayout);
        Intent intent  = getIntent();
        movieid = intent.getIntExtra("MOVIEID",1);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTrailerByLocationIdAndMovieId(1, movieid);
            }
        });
        getTrailerByLocationIdAndMovieId(1, movieid);
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
}
