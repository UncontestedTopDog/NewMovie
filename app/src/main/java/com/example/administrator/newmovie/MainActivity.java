package com.example.administrator.newmovie;


import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.newmovie.Data.DoubanMovieId;
import com.example.administrator.newmovie.Data.MaoyanMovieId;
import com.example.administrator.newmovie.Data.MovieManager;
import com.example.administrator.newmovie.Data.TimeMovieId;
import com.example.administrator.newmovie.Home.HomeFragment;
import com.example.administrator.newmovie.Homework.HomeworkFragment;
import com.example.administrator.newmovie.Me.MeFragment;
import com.example.administrator.newmovie.NetWork.NetworkHelper;
import com.example.administrator.newmovie.Review.ReViewFragment;
import com.example.administrator.newmovie.Utils.LogUtil;
import com.example.administrator.newmovie.Utils.TimeUtil;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private static final String TAG = "TApp:MainActivity";

    private static final int HOME_PAGE_TAB = 0;
    private static final int REVIEW_TAB = 1;
    private static final int HOME_WORK = 2;
    private static final int ME_TAB = 3;
    private static final int TABS_COUNT = 4;
    private Fragment[] mFragmentList = new Fragment[TABS_COUNT];
    private boolean fragmentsUpdateFlag[] = {false, false, false, false};
    private static ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private FragmentManager mFragmentManager;
    private static TextView mHomePageTab;
    private static TextView mReviewTab;
    private static TextView mHomeWorkTab;
    private static RelativeLayout mMeTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomePageTab = (TextView) findViewById(R.id.home_fragment);
        mReviewTab = (TextView) findViewById(R.id.review_fragment);
        mHomeWorkTab = (TextView) findViewById(R.id.homewoek_fragment);
        mMeTab = (RelativeLayout) findViewById(R.id.me_fragment);
        mHomePageTab.setOnClickListener(mTabOnClickListener);
        mReviewTab.setOnClickListener(mTabOnClickListener);
        mHomeWorkTab.setOnClickListener(mTabOnClickListener);
        mMeTab.setOnClickListener(mTabOnClickListener);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int idx) {
                if (idx < 0 || idx >= TABS_COUNT) {
                    return null;
                }


                if (mFragmentList[idx] != null) {
                    return mFragmentList[idx];
                }

                Fragment fragment = null;
                switch (idx) {
                    case HOME_PAGE_TAB:
                        fragment = new HomeFragment();
                        break;
                    case REVIEW_TAB:
                        fragment = new ReViewFragment();
                        break;
                    case HOME_WORK:
                        fragment = new HomeworkFragment();
                        break;

                    case ME_TAB:
                        fragment = new MeFragment();
                        break;
                    default:
                        break;
                }
                mFragmentList[idx] = fragment;
                return fragment;
            }

            @Override
            public int getCount() {
                return TABS_COUNT;
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Fragment fragment = (Fragment) super.instantiateItem(container, position);
                String fragmentTag = fragment.getTag();

                if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    ft.remove(fragment);
                    fragment = mFragmentList[position % mFragmentList.length];
                    ft.add(container.getId(), fragment, fragmentTag);
                    ft.attach(fragment);
                    ft.commit();
                    fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
                }

                return fragment;
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeIndicatorByIndex(position);
            }
        });
        changeIndicatorByIndex(0);
        mFragmentManager = getSupportFragmentManager();
    }


    private static void changeIndicatorByIndex(int index) {
        mHomePageTab.setSelected(index == HOME_PAGE_TAB);
        mMeTab.setSelected(index == ME_TAB);
        mReviewTab.setSelected(index == REVIEW_TAB);
        mHomeWorkTab.setSelected(index == HOME_WORK);
    }

    public static void switchToView(int index) {
        Log.d(TAG, "switchToView（）index = " + index);
        mViewPager.setCurrentItem(index, false);
        changeIndicatorByIndex(index);
    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int index = HOME_PAGE_TAB;
            if (view == mHomePageTab) {
                index = HOME_PAGE_TAB;
            } else if (view == mReviewTab) {
                index = REVIEW_TAB;
            } else if (view == mHomeWorkTab) {
                index = HOME_WORK;
            } else if (view == mMeTab) {
                index = ME_TAB;
            }
                switchToView(index);
        }
    };


}
