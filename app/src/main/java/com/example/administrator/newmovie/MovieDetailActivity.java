package com.example.administrator.newmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.Trailer.TrailerData;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private GradeProgress mGradeProgress;
    private TextView movieChineseName ;
    private TextView movieForeignName ;
    private TextView label ;
    private TextView style ;
    private TextView lengthFilm ;
    private TextView releaseTime ;
    private ImageView poster ;
    private TextView mGut;
    private ImageView mPull;
    private boolean b = true;
    private MovieDetail mMovieDetail ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mGradeProgress = (GradeProgress) findViewById(R.id.seek_bar);
        mGut = (TextView) findViewById(R.id.gut);
        mPull = (ImageView) findViewById(R.id.pull);
        movieChineseName = (TextView) findViewById(R.id.movie_chinese_name);
        movieForeignName = (TextView) findViewById(R.id.movie_foreign_name);
        label = (TextView) findViewById(R.id.label);
        style = (TextView) findViewById(R.id.style);
        lengthFilm = (TextView) findViewById(R.id.length_film);
        releaseTime = (TextView) findViewById(R.id.release_time);
        poster = (ImageView) findViewById(R.id.poster);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.fireworkypulltorefreshlayout);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDetailByLocationIdAndmovieId(290,224428);
            }
        });
        getDetailByLocationIdAndmovieId(290,224428);

        mPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b) {
                    startShowAnim(mPull);
                    mGut.setLines(mGut.getLineCount());
                } else {
                    startHideAnim(mPull);
                    mGut.setLines(2);
                }
                b = !b;
            }
        });
    }

    public void startShowAnim(View v) {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotateAnimation);
        set.setDuration(300);
        set.setFillAfter(true);
        v.startAnimation(set);
    }

    public void startHideAnim(View v) {
        RotateAnimation rotateAnimation = new RotateAnimation(180, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotateAnimation);
        set.setDuration(300);
        set.setFillAfter(true);
        v.startAnimation(set);
    }

    private void getDetailByLocationIdAndmovieId(int locationId , final int movieId) {
        MovieManager.INSTANCE()
                .getDetailByLocationIdAndmovieId(locationId,movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetail>() {
                    @Override
                    public void call(MovieDetail movieDetail) {
                        bindData(movieDetail);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getDetailByLocationIdAndmovieId(290,224428);
                        Log.e("MAIN",throwable.toString());
                    }
                });
    }

    private void bindData(MovieDetail movieDetail) {
        mGradeProgress.setProgress((int) (movieDetail.getData().getBasic().getOverallRating() * 10));
        mGradeProgress.showGrade();
        mGut.setText(movieDetail.getData().getBasic().getStory());
        movieChineseName.setText(movieDetail.getData().getBasic().getName());
        movieForeignName.setText(movieDetail.getData().getBasic().getNameEn());
        label = (TextView) findViewById(R.id.label);
        style = (TextView) findViewById(R.id.style);
        lengthFilm = (TextView) findViewById(R.id.length_film);
        releaseTime = (TextView) findViewById(R.id.release_time);
        ImageHelper.loadImageIntoImageView(this, movieDetail.getData().getBasic().getImg(), poster);
    }

}
