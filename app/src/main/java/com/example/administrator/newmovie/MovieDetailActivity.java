package com.example.administrator.newmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MovieDetailActivity extends AppCompatActivity {

    private GradeProgress mGradeProgress ;
    private Button btn1 , btn2 ;
    private  int i  = 50 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mGradeProgress = (GradeProgress) findViewById(R.id.seek_bar);
        mGradeProgress.setProgress(i);
        mGradeProgress.showGrade();
    }
}
