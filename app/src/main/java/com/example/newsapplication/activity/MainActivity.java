package com.example.newsapplication.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.example.newsapplication.Model.Article;
import com.example.newsapplication.Model.ResponseModel;
import com.example.newsapplication.R;
import com.example.newsapplication.Utils.OnRecyclerViewItemClickedListener;
import com.example.newsapplication.rests.ApiInterface;
import com.example.newsapplication.rests.ApiClient;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newsapplication.ui.main.SectionsPagerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        final Button btnRef = findViewById(R.id.btnRefresh);
        btnRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(getIntent());
            }
        });
    }}

