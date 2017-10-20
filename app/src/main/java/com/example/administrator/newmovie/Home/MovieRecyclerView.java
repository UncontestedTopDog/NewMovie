package com.example.administrator.newmovie.Home;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.BaseRecyclerView;
import com.example.administrator.newmovie.Data.ShowingMovie;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MovieRecyclerView extends BaseRecyclerView {

    private List<ShowingMovie.MsBean> showingMovies ;
    private MovieAdapter mAdapter;

    public MovieRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public MovieRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MovieRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        final int dividerSpace = (int) (getResources().getDisplayMetrics().density * 10);
        final int leftRightSpace = (int) (getResources().getDisplayMetrics().density * 15);
        addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int index = parent.getChildAdapterPosition(view);
                outRect.right = index != mAdapter.getItemCount() - 1 ? dividerSpace : leftRightSpace;
                if (index == 0) {
                    outRect.left = leftRightSpace;
                }
            }

        });
    }

    public void bindData(List<ShowingMovie.MsBean> showingMovies) {
        this.showingMovies = showingMovies;
        if (mAdapter == null) {
            mAdapter = new MovieAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new MovieCard(parent.getContext());
            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MovieCard) holder.itemView).bindData(showingMovies.get(position));
        }

        @Override
        public int getItemCount() {
            return showingMovies.size();
        }
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(View view) {
            super(view);
        }
    }
}
