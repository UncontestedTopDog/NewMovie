package com.example.administrator.newmovie;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.newmovie.CustomView.LoadingView;
import com.example.administrator.newmovie.CustomView.TitleBar;
import com.example.administrator.newmovie.Data.MovieImageAll;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.Review.ComingMovieFragment;
import com.example.administrator.newmovie.Review.ShowingMovieFragment;
import com.example.administrator.newmovie.StagePhoto.StagePhotoRecyclerView;
import com.google.gson.Gson;

import cn.jzvd.JZVideoPlayer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieImageAllActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private StagePhotoRecyclerView mRecyclerView;
    private TabLayout.Tab all;
    private TabLayout.Tab stage;
    private TabLayout.Tab poster;
    private TabLayout.Tab work;
    private TabLayout.Tab news;
    private TabLayout.Tab envelopell;
    private MovieImageAll movieImageAll ;
    private TitleBar mTitleBar ;

    public MovieImageAll getMovieImageAll() {
        return movieImageAll;
    }

    public void setMovieImageAll(MovieImageAll movieImageAll) {
        this.movieImageAll = movieImageAll;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_image_all);
        mTabLayout = (TabLayout) findViewById(R.id.image_style_tablayout);
        mRecyclerView = (StagePhotoRecyclerView) findViewById(R.id.photo_grid);
        mTitleBar = (TitleBar) findViewById(R.id.movie_stage_title);
        String s=getIntent().getStringExtra("MovieImageAll");
        movieImageAll = new Gson().fromJson(s,MovieImageAll.class);
        mRecyclerView.setLimit(false);
        all  = mTabLayout.newTab().setText("全部");
        stage  = mTabLayout.newTab().setText("剧照");
        poster  = mTabLayout.newTab().setText("海报");
        work  = mTabLayout.newTab().setText("工作照");
        news  = mTabLayout.newTab().setText("新闻图片");
        envelopell  = mTabLayout.newTab().setText("封面");
        mTabLayout.addTab(all);
        mTabLayout.addTab(stage);
        mTabLayout.addTab(poster);
        mTabLayout.addTab(work);
        mTabLayout.addTab(news);
        mTabLayout.addTab(envelopell);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == all)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.ALL);
                else if (tab == stage)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.STAGE);
                else if (tab == poster)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.POSTER);
                else if (tab == news)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.NEWS);
                else if (tab == work)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.WORK);
                else if (tab == envelopell)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.ENVELOPELL);
                mRecyclerView.bindData(movieImageAll);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        mTitleBar.setTitle("电影美图");
        mTitleBar.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView.bindData(movieImageAll);
    }

}
