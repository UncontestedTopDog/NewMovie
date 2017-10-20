package com.example.administrator.newmovie.Review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.Data.ComingMovie;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2017/9/18.
 */

public class ComingMovieFragment extends Fragment {
    private static final String TAG = "ComingMovieFragment";
    private ComingMovieRecyclerView mComingMovieRecyclerView;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout;
    private TextView mComingMovieDate;
    private ComingMovie mComingMovies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.coming_moive_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mComingMovieRecyclerView = (ComingMovieRecyclerView) view.findViewById(R.id.coming_movie_list);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) view.findViewById(R.id.coming_movie_refreshlayout);
        mComingMovieDate = (TextView) view.findViewById(R.id.coming_moive_date);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getComingMovieByLocationId(290);
                mComingMovieDate.setVisibility(View.GONE);
            }
        });
        getComingMovieByLocationId(290);
        mComingMovieRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
                if (mLayoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) mLayoutManager;
                    int firstItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (firstItemPosition == 0)
                        mComingMovieDate.setVisibility(View.GONE);
                    else {
                        if (mComingMovies.getMoviecomings().get(firstItemPosition).getRDay() != mComingMovies.getMoviecomings().get(firstItemPosition - 1).getRDay()||firstItemPosition == 1)
                            mComingMovieDate.setText(mComingMovies.getMoviecomings().get(firstItemPosition).getRMonth() + "月" + mComingMovies.getMoviecomings().get(firstItemPosition).getRDay() + "日");
                        if (mComingMovieDate.getVisibility() == GONE)
                            startShowAnim(mComingMovieDate);
                    }
                }
            }
        });
    }

    private void getComingMovieByLocationId(int locationId) {
        MovieManager.INSTANCE().getComingMovieByLocationId(locationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ComingMovie>() {
                    @Override
                    public void call(ComingMovie comingMovie) {
                        mComingMovies = comingMovie;
                        mComingMovieRecyclerView.bindData(comingMovie.getMoviecomings());
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getComingMovieByLocationId(290);
                        Log.e("MAIN", throwable.toString());
                    }
                });
    }

    public void startShowAnim(View view) {
        view.setVisibility(VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -view.getHeight(), 0);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(300);
        view.startAnimation(set);
    }
}
