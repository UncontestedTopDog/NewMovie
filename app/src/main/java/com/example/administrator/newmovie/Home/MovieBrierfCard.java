package com.example.administrator.newmovie.Home;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.newmovie.MainActivity;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.ShowingMovie;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MovieBrierfCard extends RelativeLayout {
    private static final String TAG = "MovieBrierfCard";
    private TextView showingAmount;
    // 即将上映的电影资料中最大显示个数
    private static final int MAX_SHOW_NUM = 15;
    public MovieBrierfCard(Context context) {
        super(context);
        initView();
    }

    public MovieBrierfCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MovieBrierfCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.showing_movie_brief_card,this);
        showingAmount = (TextView) findViewById(R.id.showing_amount);
    }


    public void bindData(final List<ShowingMovie.MsBean> showingMovies) {
        if (showingMovies.size() == 0) {
            Log.d(TAG, "bind data error showingMovies = null");
            return;
        }
        showingAmount.setText(showingMovies.size()+"部");
        showingAmount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.switchToView(1);
            }
        });
        onLoad(showingMovies);

    }

    private void onLoad(List<ShowingMovie.MsBean> showingMovies) {

        Log.i(TAG, "onLoad, replay.size: " + showingMovies.size());

        if (showingMovies.size() > 0) {
            MovieRecyclerView movieRecyclerView = (MovieRecyclerView) findViewById(R.id.movie_list);
            movieRecyclerView.bindData(showingMovies.size() > MAX_SHOW_NUM ? showingMovies.subList(0, MAX_SHOW_NUM) : showingMovies);
        }

        setVisibility(showingMovies.size() > 0 ? VISIBLE : GONE);

    }
}
