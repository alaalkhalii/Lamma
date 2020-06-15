package com.example.lamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// import android.support.design.widget.FloatingActionButton;
// import android.support.design.widget.Snackbar;
// import android.support.design.widget.TabLayout;
// import android.support.v7.app.AppCompatActivity;
// import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            tabLayout=findViewById(R.id.tabs);
            mViewPager=findViewById(R.id.viewPager);

            tabLayout.addTab(tabLayout.newTab().setText("Movie"));
            tabLayout.addTab(tabLayout.newTab().setText("Series"));

            tabLayout.setupWithViewPager(mViewPager);

            ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mViewPagerAdapter);



        }
    // Connecting the main menu to main activity
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu2_main,m);
        return true;
    }
    //actions happens when Items of the menu are selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AddShowItem:
                Intent profileIntent = new Intent(getApplicationContext(), addMovie.class);
                startActivity(profileIntent);
                return true;
            case R.id.profileItem:
               // Intent aboutIntent = new Intent(getApplicationContext(), Profile.class);
               // startActivity(aboutIntent);
                return true;
            case R.id.WatchListItem:
                // Intent aboutIntent = new Intent(getApplicationContext(), WatchList.class);
                // startActivity(aboutIntent);
                return true;
            case R.id.SearchItem:
                return true;

            default:
                return false;
        }
    }
    }

