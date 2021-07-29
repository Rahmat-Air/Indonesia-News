package com.example.indonesianews.Activies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.indonesianews.Fragments.CariFragment;
import com.example.indonesianews.Fragments.DiscoverFragment;

import com.example.indonesianews.Fragments.TentangFargment;
import com.example.indonesianews.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        loadFragments(new DiscoverFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;

//        Fungsi Berpindah Fragment
        switch (item.getItemId()) {
            case R.id.Home:
                fragment = new DiscoverFragment();
                break;

            case R.id.Cari:
                fragment = new CariFragment();
                break;

            case R.id.Tentang:
                fragment = new TentangFargment();
                break;

        }
        return loadFragments(fragment);
    }

    private boolean loadFragments(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerNav, fragment)
                    .commit();
            return true;
        }
        return false;

    }
}