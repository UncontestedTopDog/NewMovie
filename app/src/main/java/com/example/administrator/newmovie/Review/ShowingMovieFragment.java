package com.example.administrator.newmovie.Review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.administrator.newmovie.MovieManager;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.ShowingMovie;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/18.
 */

public class ShowingMovieFragment extends Fragment {
    private ShowingMovieRecyclerView mShowingMovieRecyclerView ;
    private FireworkyPullToRefreshLayout mFireworkyPullToRefreshLayout ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.showing_movie_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mShowingMovieRecyclerView = (ShowingMovieRecyclerView) view.findViewById(R.id.showing_movie_list);
        mFireworkyPullToRefreshLayout = (FireworkyPullToRefreshLayout) view.findViewById(R.id.showing_movie_refreshlayout);
        mFireworkyPullToRefreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getShowingMovieByLocationId(290);
            }
        });
        getShowingMovieByLocationId(290);
    }

    private void getShowingMovieByLocationId(int locationId) {
        MovieManager.INSTANCE()
                .getShowingMovieByLocationId(locationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShowingMovie>() {
                    @Override
                    public void call(ShowingMovie showingMovie) {
                        mShowingMovieRecyclerView.bindData(showingMovie.getMs());
                        mFireworkyPullToRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getShowingMovieByLocationId(290);
                        Log.e("MAIN",throwable.toString());
                    }
                });
    }
}
