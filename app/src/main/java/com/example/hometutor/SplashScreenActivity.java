package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView logo;
    private Animation logoAnimation;
    RelativeLayout relativeLayout1;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        relativeLayout1 = findViewById(R.id.rellay1);

        logo= findViewById(R.id.logo);
        logo.animate().alphaBy(.5f);
        logoAnimation();
        handler.postDelayed(runnable,3000);


    }

    private void logoAnimation() {

        logoAnimation = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        logo.startAnimation(logoAnimation);

    }

    public void login(View view) {
        Intent intent= new Intent(SplashScreenActivity.this,NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }
}
