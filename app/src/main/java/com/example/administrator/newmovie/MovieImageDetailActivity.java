package com.example.administrator.newmovie;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.MyPhotoView.PhotoView;
import java.util.ArrayList;
import java.util.List;

public class MovieImageDetailActivity extends BaseActivity {

    private ImagePagerAdapter imagePagerAdapter;
    private List<String> imageList = new ArrayList<>();
    private ViewPager viewPager ;
    private TextView imageAmount;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_image_detail);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        imageAmount = (TextView) findViewById(R.id.image_amount);
        imageList = getIntent().getStringArrayListExtra("imageList");
        current = getIntent().getIntExtra("CURRENT",0);
        imagePagerAdapter = new ImagePagerAdapter() ;
        viewPager.setAdapter(imagePagerAdapter);
        imageAmount.setText(current+"/"+imageList.size());
        viewPager.setCurrentItem(current);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setZoomable(true);
            photoView.setBackgroundColor(Color.BLACK);
//            Glide.with(MovieImageDetailActivity.this)
//                    .load(imageList.get(position))
//                    .centerCrop()
//                    .placeholder(R.drawable.no_pictrue)
//                    .error(R.drawable.download_fail_hint)
//                    .crossFade()
//                    .into(new GlideDrawableImageViewTarget(photoView) {
//                              @Override
//                              public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                                  super.onResourceReady(drawable, anim);
//                              }
//                          }
//                    );
            photoView.setImageURI(Uri.parse(imageList.get(position)));
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageAmount.setText(position+"/"+imageList.size());
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
