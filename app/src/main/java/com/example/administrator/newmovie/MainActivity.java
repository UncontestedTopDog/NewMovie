package com.example.administrator.newmovie;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.newmovie.Home.HomeFragment;
import com.example.administrator.newmovie.Homework.HomeworkFragment;
import com.example.administrator.newmovie.Me.MeFragment;
import com.example.administrator.newmovie.Review.ReViewFragment;

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
    private static TextView mMeTab;
    private static int mCurrTab = HOME_PAGE_TAB;
    private static RelativeLayout mNetErrorNoticeBar;
    static View fakeStatus;
    private NetworkHelper.NetworkClass netMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mNetErrorNoticeBar = (RelativeLayout) findViewById(R.id.net_error_notice_bar);
        fakeStatus = findViewById(R.id.fake_status_bar);
        mHomePageTab = (TextView) findViewById(R.id.home_textview);
        mReviewTab = (TextView) findViewById(R.id.review_textview);
        mHomeWorkTab = (TextView) findViewById(R.id.homework_textview);
        mMeTab = (TextView) findViewById(R.id.me_textview);
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
        updateNetErrorNoticeBar();
    }


    private void updateNetErrorNoticeBar() {
        mNetErrorNoticeBar.setVisibility(NetworkHelper.isDisconnected(MainActivity.this) ? View.VISIBLE : View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mNetErrorNoticeBar.getVisibility() == View.VISIBLE) {
                fakeStatus.setVisibility(View.VISIBLE);
            } else {
                if (mHomePageTab.isSelected()) {
                    fakeStatus.setVisibility(View.GONE);
                }
                if (mMeTab.isSelected()) {
                    fakeStatus.setVisibility(View.GONE);
                }
            }
        }
    }

    private static void changeIndicatorByIndex(int index) {
        mHomePageTab.setSelected(index == HOME_PAGE_TAB);
        mMeTab.setSelected(index == ME_TAB);
        mReviewTab.setSelected(index == REVIEW_TAB);
        mHomeWorkTab.setSelected(index == HOME_WORK);


        switch (index) {
            case HOME_PAGE_TAB:
                mCurrTab = HOME_PAGE_TAB;
                fakeStatus.setVisibility(View.GONE);

                break;
            case REVIEW_TAB:
                mCurrTab = REVIEW_TAB;
                fakeStatus.setVisibility(View.VISIBLE);
                break;
            case HOME_WORK:
                fakeStatus.setVisibility(View.VISIBLE);
                break;
            case ME_TAB:
                mCurrTab = ME_TAB;
                if (mNetErrorNoticeBar.getVisibility() == View.VISIBLE) {
                    fakeStatus.setVisibility(View.VISIBLE);
                } else {
                    fakeStatus.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNetChange(NetworkHelper.NetworkClass netMobile) {
        super.onNetChange(netMobile);
        updateNetErrorNoticeBar();
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
