package com.example.administrator.newmovie.Aware;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.Data.MovieAward;
import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/11/13.
 */

public class AwardDetailCard extends RelativeLayout {
    private TextView sequenceNumber;
    private TextView isWin;
    private TextView awardName;
    private TextView persons;

    public AwardDetailCard(Context context) {
        super(context);
        initView();
    }

    public AwardDetailCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AwardDetailCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.award_detail_list, this);
        sequenceNumber = (TextView) findViewById(R.id.sequence_number);
        isWin = (TextView) findViewById(R.id.is_win);
        awardName = (TextView) findViewById(R.id.award_name);
        persons = (TextView) findViewById(R.id.persons);
    }

    public void bindData(MovieAward.AwardsBean awardsBean, int position) {
        String person = "";
        if (position < awardsBean.getWinCount()) {
            sequenceNumber.setText("第" + awardsBean.getWinAwards().get(position).getSequenceNumber() + "届" + "(" + awardsBean.getWinAwards().get(position).getFestivalEventYear() + ")");
            isWin.setText("获奖");
            awardName.setText(awardsBean.getWinAwards().get(position).getAwardName());
                for (MovieAward.AwardsBean.WinAwardsBean.PersonsBean personsBean : awardsBean.getWinAwards().get(position).getPersons())
                    person += personsBean.getNameCn() + "/";
            if (person.length()<1)
                return;
                person = person.substring(0, person.length() - 1);
                persons.setText(person);
        } else {
            position = position - awardsBean.getWinCount();
            sequenceNumber.setText("第" + awardsBean.getNominateAwards().get(position).getSequenceNumber() + "届" + "(" + awardsBean.getNominateAwards().get(position).getFestivalEventYear() + ")");
            isWin.setText("提名");
            awardName.setText(awardsBean.getNominateAwards().get(position).getAwardName());
                for (MovieAward.AwardsBean.NominateAwardsBean.PersonsBeanX personsBean : awardsBean.getNominateAwards().get(position).getPersons())
                    person += personsBean.getNameCn() + "/";
            if (person.length()<1)
                return;
                person = person.substring(0, person.length() - 1);
                persons.setText(person);
        }

    }


}
