package com.example.lamma.Activity;

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

import com.example.lamma.Adapters.ViewPagerAdapter;
import com.example.lamma.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.widget.Toolbar;
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
                if (isUserLoggedIn()) {

                    Intent x = new Intent(getApplicationContext(), MovieOrSeriesActivity.class);
                    startActivity(x);
                }else{
                    Intent y = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(y);
                }
                return true;
            case R.id.profileItem:

                if (isUserLoggedIn()){
                   Intent z = new Intent(getApplicationContext(), ProfileActivity.class);
                   startActivity(z);
                }else{
                    Intent y = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(y);
                }
                return true;
            case R.id.WatchListItem:
                if (isUserLoggedIn()) {
                    Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(a);
                }else {
                    Intent y = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(y);
                }
                return true;
            case R.id.SearchItem:
                return true;
            default:
                return false;
        }
    }

    private boolean isUserLoggedIn(){
        if(FirebaseAuth.getInstance().getCurrentUser()!= null)
            return true;
        return false;
    }
}

