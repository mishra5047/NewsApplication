package com.example.newsapplication.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.Model.Article;
import com.example.newsapplication.R;
import com.example.newsapplication.Utils.OnRecyclerViewItemClickedListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {

    private List<Article> articleArrayList;
    private Context context;
    private OnRecyclerViewItemClickedListener onRecyclerViewItemClickListener;

    public MainArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;

    }
    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_main_article_adapter, viewGroup, false);
        ImageView image;
        return new MainArticleAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MainArticleAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);

        if (!TextUtils.isEmpty(articleModel.getUrlToImage())){
            Picasso.get().load(articleModel.getUrlToImage()).into(viewHolder.image);
        }
        if (!TextUtils.isEmpty(articleModel.getPublishedAt())){
            viewHolder.datepublished.setText(articleModel.getAuthor());
        }
        if(!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.titleText.setText(articleModel.getTitle());
        }
        if(!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.descriptionText.setText(articleModel.getDescription());
        }

        viewHolder.artilceAdapterParentLinear.setTag(articleModel);
    }
    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText;
        private TextView descriptionText;
        private LinearLayout artilceAdapterParentLinear;
        private TextView datepublished;
        private ImageView image;

        ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.article_adapter_tv_title);
            descriptionText = view.findViewById(R.id.article_adapter_tv_description);
            artilceAdapterParentLinear = view.findViewById(R.id.article_adapter_ll_parent);
            datepublished = view.findViewById(R.id.article_adapter_tv_publishedAt);
            image = view.findViewById(R.id.imageNews);
            artilceAdapterParentLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {


                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }

                    artilceAdapterParentLinear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onRecyclerViewItemClickListener !=null){
                                onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                            }
                        }
                    });
                }
            });
        }
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickedListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }





}
