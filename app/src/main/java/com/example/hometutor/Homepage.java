package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Homepage extends AppCompatActivity {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);


    }
}
