package com.example.administrator.newmovie.Review;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.newmovie.Data.ComingMovie;
import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ComingListCard extends RelativeLayout {

    private static final String TAG = "ComingMovieCard";

    private TextView mComingTimeMD;
    private TextView mComingTimeWeek;
    private LinearLayout mComingTimeLayout;
    private ImageView mPoster;
    private ImageView mHasForenotice;
    private TextView mComingName;
    private TextView mWantedCount;
    private TextView mType;
    private TextView mDirector;
    private TextView mActor;

    public ComingListCard(Context context) {
        super(context);
        initView();
    }

    public ComingListCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ComingListCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.coming_list_card, this);
        mComingTimeMD = (TextView) findViewById(R.id.tv_coming_time_md);
        mComingTimeWeek = (TextView) findViewById(R.id.tv_coming_time_week);
        mComingTimeLayout = (LinearLayout) findViewById(R.id.comming_time_layout);
        mPoster = (ImageView) findViewById(R.id.movie_poster);
        mHasForenotice = (ImageView) findViewById(R.id.has_forenotice);
        mComingName = (TextView) findViewById(R.id.coming_movie_name);
        mWantedCount = (TextView) findViewById(R.id.wanted_count);
        mType = (TextView) findViewById(R.id.type);
        mDirector = (TextView) findViewById(R.id.director);
        mActor = (TextView) findViewById(R.id.actor);
    }

    public boolean bindData(ComingMovie.MoviecomingsBean moviecomingsBean,boolean isshow) {
        if (moviecomingsBean == null) {
            Log.d(TAG, "bind data error comingMovie = null");
            return false;
        }
        if (isshow) {
            mComingTimeLayout.setVisibility(INVISIBLE);
        } else {
            mComingTimeLayout.setVisibility(VISIBLE);
            mComingTimeMD.setText(moviecomingsBean.getRMonth() + "." + moviecomingsBean.getRDay());
            int week = (moviecomingsBean.getRYear() % 100 + moviecomingsBean.getRYear() % 100 / 4
                    + 13 * ((moviecomingsBean.getRMonth() == 1 ? 13 : moviecomingsBean.getRMonth() == 2 ? 14 : moviecomingsBean.getRMonth()) + 1) / 5
                    + moviecomingsBean.getRDay() - 36) % 7;
            mComingTimeWeek.setText(getDayOfWeek(week));
        }
        Glide.with(getContext()).load(moviecomingsBean.getImage()).into(mPoster);
        mComingName.setText(moviecomingsBean.getTitle());
        String wantedcount = String.format("<font color=\"#DA8E6A\">%d</font><font color=\"#6E6E6E\">/%s</font>", moviecomingsBean.getWantedCount(), "人想看");
        mWantedCount.setText(Html.fromHtml(wantedcount));
        mType.setText("- " + moviecomingsBean.getType());
        mDirector.setText("导演：" + moviecomingsBean.getDirector());
        mActor.setText("主演：" + moviecomingsBean.getActor1() + "/" + moviecomingsBean.getActor2());
        return true;
    }

    private String getDayOfWeek(int week) {
        String result;
        switch (week) {
            case 1:
                result = "周一";
                break;
            case 2:
                result = "周二";
                break;
            case 3:
                result = "周三";
                break;
            case 4:
                result = "周四";
                break;
            case 5:
                result = "周五";
                break;
            case 6:
                result = "周六";
                break;
            case 7:
                result = "周日";
                break;
            default:
                result = "";
        }
        return result;
    }

}
