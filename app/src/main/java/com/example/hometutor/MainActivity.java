package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void signup(View view) {
        Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
        startActivity(intent);
    }
}
