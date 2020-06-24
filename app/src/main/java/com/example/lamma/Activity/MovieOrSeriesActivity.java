package com.example.lamma.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lamma.Activity.addMovie;
import com.example.lamma.Activity.addSeries;
import com.example.lamma.R;

public class MovieOrSeriesActivity extends AppCompatActivity {

    private ImageView mMovieImageV;
    private ImageView mSeriesImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_or_series);

        mMovieImageV = findViewById(R.id.MovieImageV);
        mSeriesImage = findViewById(R.id.SeriesImageV);

        mMovieImageV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getApplicationContext(), addMovie.class);
                startActivity(x);
            }
        });

        mSeriesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y = new Intent(getApplicationContext(), addSeries.class);
                startActivity(y);
            }
        });

    }
}
