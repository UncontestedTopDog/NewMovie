package com.example.administrator.newmovie.Trailer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */

public class TrailerRecyclerView extends RecyclerView {
    private List<TrailerData.VideoListBean> mTrailerDatas ; 
    private TrailerAdapter adapter;
    public TrailerRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public TrailerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TrailerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(VERTICAL);
        setLayoutManager(linearLayoutManager);
    }
    
    public void bindData(List<TrailerData.VideoListBean> trailerDatas){
        this.mTrailerDatas = trailerDatas ;
        if (adapter == null){
            adapter = new TrailerAdapter();
            setAdapter(adapter);
        }else adapter.notifyDataSetChanged();
    }

    private class TrailerAdapter  extends Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TrailerListCard(parent.getContext());
            return new TrailerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((TrailerListCard)holder.itemView).bindData(mTrailerDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mTrailerDatas.size();
        }

        private class TrailerViewHolder extends ViewHolder {
            public TrailerViewHolder(View view) {
                super(view);
            }
        }
    }
}
