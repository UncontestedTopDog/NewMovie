package com.example.administrator.newmovie.Review;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.ComingMovie;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ComingMovieRecyclerView extends RecyclerView {
    private List<ComingMovie.MoviecomingsBean> mComingMovies;
    private ComingMovieAdapter mAdapter;

    public ComingMovieRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public ComingMovieRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ComingMovieRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(VERTICAL);
        setLayoutManager(linearLayoutManager);
    }

    public void bindData(List<ComingMovie.MoviecomingsBean> comingMovies) {
        mComingMovies = comingMovies;
        if (mAdapter == null) {
            mAdapter = new ComingMovieAdapter();
            setAdapter(mAdapter);
        } else mAdapter.notifyDataSetChanged();

    }

    private class ComingMovieAdapter extends Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new ComingListCard(parent.getContext());
            return new ComingMovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position != 0)
                ((ComingListCard) holder.itemView).bindData(mComingMovies.get(position), mComingMovies.get(position).getRDay() == mComingMovies.get(position - 1).getRDay());
            else ((ComingListCard) holder.itemView).bindData(mComingMovies.get(position), false);
        }

        @Override
        public int getItemCount() {
            return mComingMovies.size();
        }

        private class ComingMovieViewHolder extends ViewHolder {

            public ComingMovieViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
