package com.example.administrator.newmovie.Aware;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.administrator.newmovie.Data.MovieAward;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AwardRecyclerView extends RecyclerView {
    private MovieAward movieAward ;
    private AwardAdapter mAdapter ;
    private int screenWidth ;
    public AwardRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public AwardRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AwardRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1 );
        setLayoutManager(gridLayoutManager);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth= wm.getDefaultDisplay().getWidth();
    }

    public void bindData(MovieAward movieAward){
        this.movieAward  = movieAward ;
        if (mAdapter == null) {
            mAdapter = new AwardAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class AwardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new AwardCard(parent.getContext());
            return new AwardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((AwardCard) holder.itemView).bindData(movieAward,position);
        }

        @Override
        public int getItemCount() {
            return movieAward.getAwards().size();
        }
    }

    private class AwardViewHolder extends RecyclerView.ViewHolder{
        public AwardViewHolder(View itemView) {
            super(itemView);
        }
    }


}
