package com.example.autotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tests);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.tests:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, TestFragment.newInstance()).commit();
                return true;
            case R.id.history:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, HistoryFragment.newInstance()).commit();
                return true;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, AboutFragment.newInstance()).commit();
                return true;
        }
        return false;
    }
}