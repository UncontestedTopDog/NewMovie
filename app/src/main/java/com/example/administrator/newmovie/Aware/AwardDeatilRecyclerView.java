package com.example.administrator.newmovie.Aware;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.Data.MovieAward;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AwardDeatilRecyclerView extends RecyclerView {
    private MovieAward.AwardsBean awardsBean ;
    private AwardAdapter mAdapter ;

    public AwardDeatilRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public AwardDeatilRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AwardDeatilRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1 );
        setLayoutManager(gridLayoutManager);
    }

    public void bindData(MovieAward.AwardsBean awardsBean){
        this.awardsBean  = awardsBean ;
        if (mAdapter == null) {
            mAdapter = new AwardAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class AwardAdapter extends Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new AwardDetailCard(parent.getContext());
            return new AwardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((AwardDetailCard) holder.itemView).bindData(awardsBean,position);
        }

        @Override
        public int getItemCount() {
            return awardsBean.getWinCount()+awardsBean.getNominateCount();
        }
    }

    private class AwardViewHolder extends ViewHolder{
        public AwardViewHolder(View itemView) {
            super(itemView);
        }
    }


}
