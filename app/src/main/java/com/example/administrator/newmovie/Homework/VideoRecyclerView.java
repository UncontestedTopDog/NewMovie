package com.example.administrator.newmovie.Homework;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.Data.TouTiaoVideoData;
import com.example.administrator.newmovie.Data.TrailerData;
import com.example.administrator.newmovie.Data.XiGuaMovieData;
import com.example.administrator.newmovie.Trailer.TrailerListCard;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */

public class VideoRecyclerView extends RecyclerView {
//    private List<XiGuaMovieData> mXiGuaMovieDatas ;
    private List<TouTiaoVideoData> mTouTiaoVideoDatas ;
    private VideoAdapter adapter;
    private String mClassName ;
    public VideoRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public VideoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VideoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(VERTICAL);
        setLayoutManager(linearLayoutManager);
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    //    public void bindData(List<XiGuaMovieData> mXiGuaMovieDatas){
//        this.mXiGuaMovieDatas = mXiGuaMovieDatas ;
//        if (adapter == null){
//            adapter = new VideoAdapter();
//            setAdapter(adapter);
//        }else adapter.notifyDataSetChanged();
//    }


    public void bindData(List<TouTiaoVideoData> mTouTiaoVideoDatas){
        this.mTouTiaoVideoDatas = mTouTiaoVideoDatas ;
        if (adapter == null){
            adapter = new VideoAdapter();
            setAdapter(adapter);
        }else adapter.notifyDataSetChanged();
    }


    private class VideoAdapter  extends Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            VideoListCard view = new VideoListCard(parent.getContext());
            view.setmClassName(mClassName);
            return new TrailerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((VideoListCard)holder.itemView).setmClassName(mClassName);
            ((VideoListCard)holder.itemView).bindData(mTouTiaoVideoDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mTouTiaoVideoDatas.size();
        }

        private class TrailerViewHolder extends ViewHolder {
            public TrailerViewHolder(View view) {
                super(view);
            }
        }
    }

}
