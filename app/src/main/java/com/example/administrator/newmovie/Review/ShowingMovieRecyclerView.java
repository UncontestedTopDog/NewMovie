package com.example.administrator.newmovie.Review;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.BaseRecyclerView;
import com.example.administrator.newmovie.Data.ShowingMovie;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/9/19.
 */

public class ShowingMovieRecyclerView extends BaseRecyclerView {
    private List<ShowingMovie.MsBean> mShowingMovie = new ArrayList<>();
    private ShowingMovieAdapter mAdapter;
    public ShowingMovieRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public ShowingMovieRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ShowingMovieRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(VERTICAL);
        setLayoutManager(linearLayoutManager);
//        final int dividerSpace = (int) (getResources().getDisplayMetrics().density * 10);
//        final int leftRightSpace = (int) (getResources().getDisplayMetrics().density * 15);
//        addItemDecoration(new ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
//                int index = parent.getChildAdapterPosition(view);
//                outRect.right = index != mAdapter.getItemCount() - 1 ? dividerSpace : leftRightSpace;
//                if (index == 0) {
//                    outRect.left = leftRightSpace;
//                }
//            }
//        });
    }

    public void bindData(List<ShowingMovie.MsBean> showingMovies){
        mShowingMovie = showingMovies;
        if (mAdapter == null){
            mAdapter = new ShowingMovieAdapter();
            setAdapter(mAdapter);
        }else mAdapter.notifyDataSetChanged();
    }

    private class ShowingMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new ShowingListCard(parent.getContext());
            return new ShowingMovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ShowingListCard)holder.itemView).bindData(mShowingMovie.get(position));
        }

        @Override
        public int getItemCount() {
            return mShowingMovie.size();
        }
    }

    private class ShowingMovieViewHolder extends RecyclerView.ViewHolder {
        public ShowingMovieViewHolder(View view) {
            super(view);
        }
    }
}
