package com.example.administrator.newmovie.Home;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.newmovie.MovieDetailActivity;
import com.example.administrator.newmovie.R;
import com.example.administrator.newmovie.Data.ShowingMovie;

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
    private LinearLayout mainLayout ;
    private int movieId;
    private String movieName;

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
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        poster = (ImageView) findViewById(R.id.showing_movie_poster);
        label = (TextView) findViewById(R.id.label);
        grade = (TextView) findViewById(R.id.showing_movie_grade);
        name = (TextView) findViewById(R.id.showing_movie_name);
        mainLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("MOVIEID",movieId);
                intent.putExtra("MOVIENAME",movieName);
                getContext().startActivity(intent);
            }
        });
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

        movieId = showingMovie.getId();
        movieName = showingMovie.getT();

//        Glide.with(getContext()).load(showingMovie.getImg()).into(poster);

        Glide.with(getContext())
                .load(showingMovie.getImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(poster) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );

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
