package com.example.administrator.newmovie.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.Data.ShowingMovie;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/9/13.
 */

public class HomeFragment extends Fragment {

    private MZBannerView mMZBanner;
    private String[] RES = {
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg",
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg",
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg",
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg",
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg",
            "https://pic1.zhimg.com/4c88a7da3147a213d76f918188d1b0f4_r.jpg"};
    private MovieBrierfCard mMovieBrierfCard;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
//         设置页面点击事件
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });

        List<String> list = new ArrayList<>();
        for (int i = 0; i < RES.length; i++) {
            list.add(RES[i]);
        }
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        mMovieBrierfCard = (MovieBrierfCard) view.findViewById(R.id.showing_movice);
        getShowingMovieByLocationId(290);


    }

    public class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
//            Glide.with(getContext()).load(data).into(mImageView);
            Glide.with(getContext())
                    .load(data)
                    .centerCrop()
                    .placeholder(R.drawable.no_pictrue)
                    .error(R.drawable.download_fail_hint)
                    .crossFade()
                    .into(new GlideDrawableImageViewTarget(mImageView) {
                              @Override
                              public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                                  super.onResourceReady(drawable, anim);
                              }
                          }
                    );
        }

    }

    private void getShowingMovieByLocationId(int locationId) {
        MovieManager.INSTANCE()
                .getShowingMovieByLocationId(locationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShowingMovie>() {
                    @Override
                    public void call(ShowingMovie showingMovie) {
                        mMovieBrierfCard.bindData(showingMovie.getMs());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getShowingMovieByLocationId(290);
                        Log.e("MAIN", throwable.toString());
                    }
                });
    }
}
