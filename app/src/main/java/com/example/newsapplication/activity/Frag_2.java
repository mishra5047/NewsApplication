package com.example.newsapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.Model.Article;
import com.example.newsapplication.Model.ResponseModel;
import com.example.newsapplication.R;
import com.example.newsapplication.Utils.OnRecyclerViewItemClickedListener;
import com.example.newsapplication.adapter.MainArticleAdapter;
import com.example.newsapplication.rests.ApiClient;
import com.example.newsapplication.rests.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_2 extends Fragment implements OnRecyclerViewItemClickedListener {

    private String API_KEY = "eb6839b5fc7f4e3ebef6b3e90391ce74";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.frag_2_layout, container, false);
        final View view = inflater.inflate(R.layout.frag_2_layout, container, false);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("TechCrunch", API_KEY);
        ((Call) call).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null && response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();

                    if (articleList.size() > 0) {

                        final RecyclerView mainRecycler = view.findViewById(R.id.activity_main_rv_2);

                        final LinearLayoutManager linearLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                        MainArticleAdapter adapter = new MainArticleAdapter(articleList);

                        mainRecycler.setLayoutManager(linearLayout);
                        //                        mainRecycler.setAdapter(adapter);

                        final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList);
                        mainArticleAdapter.setOnRecyclerViewItemClickListener(Frag_2.this);
                        mainRecycler.setAdapter(mainArticleAdapter);


                    }
                }
            }



            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("out", t.toString());
            }
        });

        return view;

    }

    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.article_adapter_ll_parent:
                Article article = (Article) view.getTag();
                if(!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(getContext(), WebActivity.class);
                    webActivity.putExtra("url",article.getUrl());
                    startActivity(webActivity);
                }
                break;
        }
    }

}
