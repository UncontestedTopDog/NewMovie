package com.example.administrator.newmovie.Aware;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.newmovie.BaseActivity;
import com.example.administrator.newmovie.Data.MovieAward;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AwardActivity extends BaseActivity {

    private static final String TAG = "AwardActivity";

    private int movieId ;

    private AwardBriefCard awardBriefCard ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        movieId = intent.getIntExtra("MOVIEID",224428);
        setContentView(R.layout.activity_award);
        awardBriefCard = (AwardBriefCard) findViewById(R.id.award_brief_card);
        getAwardByLocationIdAndMovieId(290, movieId);
        awardBriefCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awardBriefCard.getVisibility()==View.VISIBLE)
                    awardBriefCard.startHideAnim(null);
                else awardBriefCard.startShowAnim(null);
            }
        });
    }

    private void getAwardByLocationIdAndMovieId(int locationId, final int movieId) {
        MovieManager.INSTANCE()
                .getAwardByLocationIdAndMovieId(locationId, movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieAward>() {
                    @Override
                    public void call(MovieAward movieAward) {
                        awardBriefCard.bindData(movieAward);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getAwardByLocationIdAndMovieId(290, movieId);
                        Log.e(TAG, throwable.toString());
                    }
                });
    }
}
