package com.example.administrator.newmovie;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.newmovie.Data.MovieDetail;

/**
 * Created by Administrator on 2017/11/6.
 */

public class BoxOfficeCard extends LinearLayout {
    private TextView ranking;
    private TextView todayBoxDes;
    private TextView totalBoxDes;
    private TextView todayBoxDesUnit;
    private TextView totalBoxDesUnit;

    public BoxOfficeCard(Context context) {
        super(context);
        initView();
    }

    public BoxOfficeCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BoxOfficeCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.box_office_card, this);
        ranking = (TextView) findViewById(R.id.box_office_ranking);
        todayBoxDes = (TextView) findViewById(R.id.today_box_des);
        totalBoxDes = (TextView) findViewById(R.id.total_box_des);
        todayBoxDesUnit = (TextView) findViewById(R.id.today_box_des_unit);
        totalBoxDesUnit = (TextView) findViewById(R.id.total_box_des_unit);
    }

    public void bindData(MovieDetail.DataBean.BoxOfficeBean boxOfficeBean) {
        if (boxOfficeBean.getRanking() == 0) {
            setVisibility(GONE);
            return;
        }
        if (boxOfficeBean.getTodayBoxDes() != null) {
            ranking.setText(boxOfficeBean.getRanking() + "");
            todayBoxDes.setText(boxOfficeBean.getTodayBoxDes() + "");
            totalBoxDes.setText(boxOfficeBean.getTotalBoxDes() + "");
            todayBoxDesUnit.setText(boxOfficeBean.getTodayBoxDesUnit() + "");
            totalBoxDesUnit.setText(boxOfficeBean.getTotalBoxUnit() + "");
        } else setVisibility(GONE);
    }
}
