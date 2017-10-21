package com.example.administrator.newmovie.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.newmovie.DirectorAndActors.DirectorAndActorCard;
import com.example.administrator.newmovie.DirectorAndActors.DirectorAndActorRecyclerView;
import com.example.administrator.newmovie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */

public class NineGridView extends LinearLayout {
    private RecyclerView nineTGridList ;
    private List<String> imageList = new ArrayList<>();
    public NineGridView(Context context) {
        super(context);
    }

    public NineGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NineGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.nine_gird_view,this);
        nineTGridList = (RecyclerView) findViewById(R.id.nine_gird_list);
        nineTGridList.setAdapter(new NineGridListAdapter());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        nineTGridList.setLayoutManager(gridLayoutManager);
    }

    public void bindData(List<String> imageList){
        this.imageList = imageList ;
    }

    private class NineGridListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new ImageView(parent.getContext());
            return new NineGridListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Glide.with(getContext()).load(imageList.get(position)).into((ImageView) holder.itemView);
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }
    }

    private class NineGridListViewHolder extends RecyclerView.ViewHolder {
        public NineGridListViewHolder(View view) {
            super(view);
        }
    }
}
