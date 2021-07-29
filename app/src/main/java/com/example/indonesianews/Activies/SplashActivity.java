package com.example.indonesianews.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.indonesianews.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Waktu Splahscreen Muncul
        int waktu_loading = 3000;
        new Handler().postDelayed(() -> {

//            Setelah Splashscreen Otomatis Berpindah ke Navigasi Activity
            Intent home=new Intent(SplashActivity.this, NavActivity.class);
            startActivity(home);
            finish();

        }, waktu_loading);
    }
}