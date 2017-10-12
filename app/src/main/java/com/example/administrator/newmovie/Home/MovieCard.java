package com.example.administrator.newmovie.Home;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.newmovie.ImageHelper;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.ShowingMovie;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MovieCard extends RelativeLayout {

    private static final String TAG = "ShowingMovieCard";
    private boolean is3d = false;
    private boolean isimax = false;

    private ImageView poster;

    private TextView label;
    private TextView grade;
    private TextView name;

    public MovieCard(Context context) {
        super(context);
        initView();
    }

    public MovieCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.showing_movie_card, this);
        poster = (ImageView) findViewById(R.id.showing_movie_poster);
        label = (TextView) findViewById(R.id.label);
        grade = (TextView) findViewById(R.id.showing_movie_grade);
        name = (TextView) findViewById(R.id.showing_movie_name);
    }

    public boolean bindData(final ShowingMovie.MsBean showingMovie) {
        if (showingMovie == null) {
            Log.d(TAG, "bind data error showingMovie = null");
            return false;
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        ImageHelper.loadImageIntoImageView(getContext(), showingMovie.getImg(), poster);

        grade.setText(showingMovie.getR() + "");
        grade.setVisibility(showingMovie.getR() > 0 ? VISIBLE : INVISIBLE);
        for (int i = 0; i < showingMovie.getVersions().size(); i++) {
            switch (showingMovie.getVersions().get(i).getEnumX()) {
                case 1:
                    break;
                case 2:
                    is3d = true;
                    break;
                case 3:
                    isimax = true;
                    break;
                case 4:
                    is3d = true;
                    isimax = true;
                    break;
                default:
                    break;
            }
        }
        if (is3d && isimax)
            label.setText("IMAX3D");
        else {
            if (is3d)
                label.setText("3D");
            else if (isimax)
                label.setText("IMAX");
            else label.setVisibility(INVISIBLE);
        }
        name.setText(showingMovie.getT());
        return true;
    }

}
