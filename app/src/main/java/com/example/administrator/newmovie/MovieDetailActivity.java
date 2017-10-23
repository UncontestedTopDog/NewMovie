package com.example.administrator.newmovie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.CustomView.GradeProgress;
import com.example.administrator.newmovie.CustomView.LoadingView;
import com.example.administrator.newmovie.CustomView.NineGridView;
import com.example.administrator.newmovie.Data.MovieDetail;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.DirectorAndActors.DirectorAndActorBrierfCard;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

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
    private MovieDetail mMovieDetail;
    private LoadingView loadingView ;
    private DirectorAndActorBrierfCard directorAndActorBrierfCard ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout;
    private int movieId ;
//    private NineGridView nineGridView ;
    private List<String> imageList = new ArrayList<>();
    private RecyclerView nineGridList ;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        movieId = intent.getIntExtra("MOVIEID",224428);
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
        loadingView = (LoadingView) findViewById(R.id.loading);
//        nineGridView = (NineGridView) findViewById(R.id.nine_grid_view);
        nineGridList = (RecyclerView) findViewById(R.id.nine_gird_list);
        myAdapter = new MyAdapter(this);
        nineGridList.setLayoutManager(new GridLayoutManager(this, 3));
        nineGridList.setAdapter(myAdapter);
        directorAndActorBrierfCard = (DirectorAndActorBrierfCard) findViewById(R.id.director_and_actor_brierf_card);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) findViewById(R.id.fireworkypulltorefreshlayout);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDetailByLocationIdAndMovieId(290, movieId);
            }
        });
        getDetailByLocationIdAndMovieId(290, movieId);

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

    private void getDetailByLocationIdAndMovieId(int locationId, final int movieId) {
        MovieManager.INSTANCE()
                .getDetailByLocationIdAndMovieId(locationId, movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetail>() {
                    @Override
                    public void call(MovieDetail movieDetail) {
                        bindData(movieDetail);
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getDetailByLocationIdAndMovieId(290, movieId);
                        Log.e("MAIN", throwable.toString());
                    }
                });
    }

    private void bindData(MovieDetail movieDetail) {
        mGradeProgress.setProgress((int) (movieDetail.getData().getBasic().getOverallRating() * 10));
        mGradeProgress.showGrade();
        mGut.setText("剧情：" + movieDetail.getData().getBasic().getStory());
        movieChineseName.setText(movieDetail.getData().getBasic().getName());
        movieForeignName.setText(movieDetail.getData().getBasic().getNameEn());
        String s = "";
        if (movieDetail.getData().getBasic().isIs3D())
            s += "3D";
        if (movieDetail.getData().getBasic().isIsDMAX() || movieDetail.getData().getBasic().isIsIMAX())
            s += "IMAX";
        label.setText(s);
        if (s.length()<3)
            label.setBackgroundColor(Color.TRANSPARENT);
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
        Glide.with(this).load(movieDetail.getData().getBasic().getImg()).into(poster);
        loadingView.setVisibility(View.GONE);
        directorAndActorBrierfCard.bindData(movieDetail.getData().getBasic().getActors(),movieDetail.getData().getBasic().getDirector());
        for (MovieDetail.DataBean.BasicBean.StageImgBean.ListBean listBean :movieDetail.getData().getBasic().getStageImg().getList())
        {
            imageList.add(listBean.getImgUrl());
        }
        myAdapter.notifyDataSetChanged();

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private Context context;
        private Resources resources;
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            this.context = context;
            this.resources = context.getResources();
            inflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.nine_gird_view, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position){
            Glide.with(getBaseContext()).load(imageList.get(position)).into(holder.image);
        }


        @Override
        public int getItemCount() {
            return imageList.size()>9? 9 :imageList.size() ;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
