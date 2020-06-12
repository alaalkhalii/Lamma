package com.example.lamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// import android.support.design.widget.FloatingActionButton;
// import android.support.design.widget.Snackbar;
// import android.support.design.widget.TabLayout;
// import android.support.v7.app.AppCompatActivity;
// import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import com.google.android.material.floatingactionbutton.*;
import com.google.android.material.snackbar.*;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

        TabLayout tabLayout;
        ViewPager mViewPager;
        Button addMov;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            tabLayout=findViewById(R.id.tabs);
            mViewPager=findViewById(R.id.viewPager);
            addMov= findViewById(R.id.add);

            tabLayout.addTab(tabLayout.newTab().setText("Movie"));
            tabLayout.addTab(tabLayout.newTab().setText("Series"));

            tabLayout.setupWithViewPager(mViewPager);

            ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mViewPagerAdapter);



            addMov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent x = new Intent(MainActivity.this,addMovie.class);
                    startActivity(x);
                }
            });


        }
    }

