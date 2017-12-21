package com.example.administrator.newmovie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.Aware.AwardActivity;
import com.example.administrator.newmovie.CustomView.GradeProgress;
import com.example.administrator.newmovie.CustomView.LoadingView;
import com.example.administrator.newmovie.Data.MovieDetail;
import com.example.administrator.newmovie.Data.MovieImageAll;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.DirectorAndActors.DirectorAndActorBrierfCard;
import com.example.administrator.newmovie.StagePhoto.StagePhotoBrierfCard;
import com.example.administrator.newmovie.Trailer.TrailerListActivity;

import cn.jzvd.JZVideoPlayer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends BaseActivity {

    private static final String TAG = "MovieDetailActivity" ;
    private GradeProgress mGradeProgress;
    private TextView movieChineseName;
    private TextView movieForeignName;
    private TextView label;
    private TextView style;
    private TextView lengthFilm;
    private TextView releaseTime;
    private ImageView poster;
    private TextView mGut;
    private ImageView mPull;
    private boolean b = true;
    private LoadingView loadingView ;
    private DirectorAndActorBrierfCard directorAndActorBrierfCard ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout;
    private int movieId ;
    private String movieName ;
    private StagePhotoBrierfCard stagePhotoBrierfCard ;
    private TextView trailerAll ;
    private NewJZVideoPlayerStandard trailerPlayer ;
    private BoxOfficeCard boxOfficeCard ;
    private LinearLayout awardLayout ;
    private TextView winAward ;
    private TextView nominationAward ;
    private TextView scoreView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        movieId = intent.getIntExtra("MOVIEID",224428);
//        movieId = 12135 ;
        movieName = intent.getStringExtra("MOVIENAME");
        findViewById();

        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovieImageAllByMovieId(movieId);
            }
        });
        getMovieImageAllByMovieId(movieId);



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

        trailerAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this,TrailerListActivity.class);
                intent.putExtra("MOVIENAME",movieName);
                intent.putExtra("MOVIEID",movieId);
                startActivity(intent);
            }
        });

        awardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MovieDetailActivity.this,AwardActivity.class);
                intent1.putExtra("MOVIEID",movieId);
                startActivity(intent1);
            }
        });

    }

    private void findViewById() {
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
        loadingView = (LoadingView) findViewById(R.id.loading_view);
        stagePhotoBrierfCard = (StagePhotoBrierfCard) findViewById(R.id.stage_photo_brierf_card);
        directorAndActorBrierfCard = (DirectorAndActorBrierfCard) findViewById(R.id.director_and_actor_brierf_card);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.fireworkypulltorefreshlayout);
        trailerAll = (TextView) findViewById(R.id.trailer_all);
        trailerPlayer = (NewJZVideoPlayerStandard) findViewById(R.id.trailer_player);
        boxOfficeCard = (BoxOfficeCard) findViewById(R.id.box_office_card);
        awardLayout = (LinearLayout) findViewById(R.id.award_layout);
        winAward = (TextView) findViewById(R.id.win_award);
        nominationAward = (TextView) findViewById(R.id.nomination_award);
        scoreView = (TextView) findViewById(R.id.s1);
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

    private void getDetailByLocationIdAndMovieId(int locationId, final int movieId) {
        MovieManager.INSTANCE()
                .getDetailByLocationIdAndMovieId(locationId, movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetail>() {
                    @Override
                    public void call(MovieDetail movieDetail) {
                        bindData(movieDetail);
                        boxOfficeCard.bindData(movieDetail.getData().getBoxOffice());
                        bindAwardData(movieDetail.getData().getBasic().getAward());
                        getTrailerByLocationIdAndMovieId(1, movieId);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG,"2"+ throwable.toString());
                    }
                });
    }

    private void bindAwardData(MovieDetail.DataBean.BasicBean.AwardBean movieAward) {
        if (movieAward.getTotalNominateAward() ==0 && movieAward.getTotalWinAward() == 0 ){
            awardLayout.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            return;
        }
        nominationAward.setText("提名次数:"+movieAward.getTotalNominateAward());
        winAward.setText("获奖次数:"+movieAward.getTotalWinAward());
        loadingView.setVisibility(View.GONE);
    }


    private void getMovieImageAllByMovieId(final int movieId) {
        MovieManager.INSTANCE()
                .getMovieImageAllByMovieId(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieImageAll>() {
                    @Override
                    public void call(MovieImageAll movieImageAll) {
                        stagePhotoBrierfCard.bindData(movieImageAll);
                        getDetailByLocationIdAndMovieId(290, movieId);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "1"+throwable.toString());
                    }
                });
    }

    private void bindData(MovieDetail movieDetail) {
        mGradeProgress.setProgress((int) (movieDetail.getData().getBasic().getOverallRating() * 10));
        mGradeProgress.showGrade();
        scoreView.setText(movieDetail.getData().getBasic().getOverallRating()+"");
        mGut.setText("剧情：" + movieDetail.getData().getBasic().getStory());
        movieChineseName.setText(movieDetail.getData().getBasic().getName());
        movieForeignName.setText(movieDetail.getData().getBasic().getNameEn());
        String s = "";
        if (movieDetail.getData().getBasic().isIs3D())
            s += "3D";
        if (movieDetail.getData().getBasic().isIsDMAX() || movieDetail.getData().getBasic().isIsIMAX())
            s += "IMAX";
        label.setText(s);
        s = "";
        for (String s1 : movieDetail.getData().getBasic().getType())
            s += s1 + "/";
        s = s.substring(0, s.length() - 1);
        style.setText(s);
        lengthFilm.setText("片长：" + movieDetail.getData().getBasic().getMins());
        s = movieDetail.getData().getBasic().getReleaseDate();
        StringBuilder sb = new StringBuilder(s);
        sb = sb.insert(6, "-");
        sb = sb.insert(4, "-");
        s = sb.toString() + movieDetail.getData().getBasic().getReleaseArea();
        releaseTime.setText(s);
        Glide.with(this)
                .load(movieDetail.getData().getBasic().getImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(poster) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        directorAndActorBrierfCard.bindData(movieDetail.getData().getBasic().getActors(),movieDetail.getData().getBasic().getDirector());
    }


    private void getTrailerByLocationIdAndMovieId(int pageindex , final int movieid) {
        MovieManager.INSTANCE()
                .getTrailerByPageIndexAndMovieId(pageindex,movieid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrailerData>() {
                    @Override
                    public void call(TrailerData trailerData) {
                        trailerPlayer.setUp(trailerData.getVideoList().get(0).getHightUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL, "");
                        Glide.with(MovieDetailActivity.this)
                                .load(trailerData.getVideoList().get(0).getImage())
                                .centerCrop()
                                .placeholder(R.drawable.no_pictrue)
                                .error(R.drawable.download_fail_hint)
                                .crossFade()
                                .into(new GlideDrawableImageViewTarget(trailerPlayer.thumbImageView) {
                                          @Override
                                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                                              super.onResourceReady(drawable, anim);
                                          }
                                      }
                                );
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getTrailerByLocationIdAndMovieId(1,movieid);
                        Log.e(TAG,throwable.toString());
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
