package com.example.administrator.newmovie.Review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ReViewFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayout.Tab showing ;
    private TabLayout.Tab coming ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.review_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.movie_tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.movie_viewpager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            private String[] mTitles = new String[]{"正在热映", "即将上映"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return new ShowingMovieFragment();
                else
                    return new ComingMovieFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        showing = mTabLayout.getTabAt(0);
        coming = mTabLayout.getTabAt(1);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == showing)
                    mViewPager.setCurrentItem(0);
                else
                    mViewPager.setCurrentItem(1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0 )
                    showing.select();
                else coming.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
