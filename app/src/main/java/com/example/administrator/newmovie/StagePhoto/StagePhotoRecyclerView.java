package com.example.administrator.newmovie.StagePhoto;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.BaseRecyclerView;
import com.example.administrator.newmovie.Data.MovieImageAll;
import com.example.administrator.newmovie.Data.ShowingMovie;
import com.example.administrator.newmovie.Home.MovieCard;
import com.example.administrator.newmovie.MovieDetailActivity;
import com.example.administrator.newmovie.MovieImageAllActivity;
import com.example.administrator.newmovie.MovieImageDetailActivity;
import com.example.administrator.newmovie.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class StagePhotoRecyclerView extends BaseRecyclerView {

    public static final int ALL = 1;
    public static final int STAGE = 2;
    public static final int POSTER = 3;
    public static final int WORK = 4;
    public static final int NEWS = 5;
    public static final int ENVELOPELL = 6;
    private StagePhotoAdapter mAdapter;
    private List<String> imageList = new ArrayList<>();
    private static final int MAX_SHOW_NUM = 9;
    private boolean limit = true ;
    private int style  = ALL ;

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public StagePhotoRecyclerView(Context context) {
        super(context);
        initView();
    }

    public StagePhotoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StagePhotoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    public void bindData(MovieImageAll movieImageAll) {

        imageList.clear();

        for (int i = 0; i < movieImageAll.getImages().size(); i++) {
            if (limit) {
                if (movieImageAll.getImages().get(i).getType() != 1)
                    imageList.add(movieImageAll.getImages().get(i).getImage());
            }else {
                switch (style){
                    case ALL:
                        imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                    case STAGE:
                        if (movieImageAll.getImages().get(i).getType() == 6)
                            imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                    case POSTER:
                        if (movieImageAll.getImages().get(i).getType() == 1)
                            imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                    case WORK:
                        if (movieImageAll.getImages().get(i).getType() == 41)
                            imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                    case NEWS:
                        if (movieImageAll.getImages().get(i).getType() == 31)
                            imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                    case ENVELOPELL:
                        if (movieImageAll.getImages().get(i).getType() == 21)
                            imageList.add(movieImageAll.getImages().get(i).getImage());
                        break;
                }
            }
        }
        if (mAdapter == null) {
            mAdapter = new StagePhotoAdapter(getContext());
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class StagePhotoAdapter extends RecyclerView.Adapter<StagePhotoViewHolder> {

        private LayoutInflater inflater;

        public StagePhotoAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public StagePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            WindowManager wm = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            width -= 30 * width / 360;
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(width / 3 - 10, width / 3 - 10));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imageView.getLayoutParams());
            lp.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(lp);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            return new StagePhotoViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(final StagePhotoViewHolder holder, final int position) {
            Glide.with(getContext())
                    .load(imageList.get(position))
                    .centerCrop()
                    .placeholder(R.drawable.no_pictrue)
                    .error(R.drawable.download_fail_hint)
                    .crossFade()
                    .into(new GlideDrawableImageViewTarget((ImageView) holder.itemView) {
                              @Override
                              public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                                  super.onResourceReady(drawable, anim);
                              }
                          }
                    );
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), MovieImageDetailActivity.class);

                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) imageList);
                    intent.putExtra("CURRENT", position);

                    getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (limit)
            return imageList.size() > MAX_SHOW_NUM ? MAX_SHOW_NUM : imageList.size();
            else return imageList.size();
        }
    }

    private class StagePhotoViewHolder extends RecyclerView.ViewHolder {

        public StagePhotoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
