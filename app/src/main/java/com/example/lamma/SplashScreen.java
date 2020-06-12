package com.example.lamma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    //Declaration and initialization for the splash screen timer
    private int SPLASH_TIMER =3000;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext=SplashScreen.this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent x = new Intent(mContext,MainActivity.class);
                startActivity(x);
                finish();
            }
        },SPLASH_TIMER);
    }

    //Send to any (Activity) and finish
    private void sendToActivityAndFinish(Class<? extends Activity> activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
        finish();
    }
}
