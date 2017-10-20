package com.example.administrator.newmovie.DirectorAndActors;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.BaseRecyclerView;
import com.example.administrator.newmovie.Data.MovieDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class DirectorAndActorRecyclerView  extends BaseRecyclerView {

    private List<MovieDetail.DataBean.BasicBean.ActorsBean> actorsBeanList ;
    private MovieDetail.DataBean.BasicBean.DirectorBean directorBean ;
    private DirectorAndActorAdapter mAdapter;

    public DirectorAndActorRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public DirectorAndActorRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DirectorAndActorRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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

    public void bindData(List<MovieDetail.DataBean.BasicBean.ActorsBean> actorsBeanList, MovieDetail.DataBean.BasicBean.DirectorBean directorBean) {
        this.actorsBeanList = actorsBeanList;
        this.directorBean = directorBean ;
        if (mAdapter == null) {
            mAdapter = new DirectorAndActorAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class DirectorAndActorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new DirectorAndActorCard(parent.getContext());
            return new DirectorAndActorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position == 0)
                ((DirectorAndActorCard) holder.itemView).bindData(directorBean);
            else
            ((DirectorAndActorCard) holder.itemView).bindData(actorsBeanList.get(position-1));
        }

        @Override
        public int getItemCount() {
            return actorsBeanList.size()+1;
        }
    }

    private class DirectorAndActorViewHolder extends RecyclerView.ViewHolder {
        public DirectorAndActorViewHolder(View view) {
            super(view);
        }
    }
}

