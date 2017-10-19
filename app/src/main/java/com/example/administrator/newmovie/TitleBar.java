package com.example.administrator.newmovie;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/11.
 */

public class TitleBar extends RelativeLayout {

    private TextView title ;
    private ImageButton back ;

    public TitleBar(Context context) {
        super(context);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.title_bar,this);
        title = (TextView) findViewById(R.id.trailer_title);
        back = (ImageButton) findViewById(R.id.back);
    }

    public void setTitle(String s){
        title.setText(s+"预告片");
    }

    public void setBackListener (OnClickListener onClickListener){
        back.setOnClickListener(onClickListener);
    }
}
