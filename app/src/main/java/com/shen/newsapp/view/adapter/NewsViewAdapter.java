package com.shen.newsapp.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shen.newsapp.R;
import com.shen.newsapp.WebViewActivity;
import com.shen.newsapp.model.bean.NewsBean;
import com.shen.newsapp.view.MyImageView;

import java.io.IOException;
import java.util.List;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {
    List<NewsBean.ResultBean.DataBean> beans;

    public NewsViewAdapter(NewsBean beans) {
        this.beans = beans.getResult().getData();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_new_news_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsBean.ResultBean.DataBean bean = beans.get(position);
        holder.title.setText(bean.getTitle());
        if (bean.getThumbnail_pic_s()!=null)
            Glide.with(holder.itemView.getContext()).load(preExecuteUrl(bean.getThumbnail_pic_s())).placeholder(R.drawable.ic_launcher_background).into(holder.thumbnail_pic_s);
        if (bean.getThumbnail_pic_s02()!=null)
            Glide.with(holder.itemView.getContext()).load(preExecuteUrl(bean.getThumbnail_pic_s02())).placeholder(R.drawable.ic_launcher_background).into(holder.thumbnail_pic_s02);
        if (bean.getThumbnail_pic_s03()!=null)
            Glide.with(holder.itemView.getContext()).load(preExecuteUrl(bean.getThumbnail_pic_s03())).placeholder(R.drawable.ic_launcher_background).into(holder.thumbnail_pic_s03);

        holder.date.setText(bean.getDate());
        holder.author_name.setText(bean.getAuthor_name());
        holder.itemView.setOnClickListener(view -> {
            String url = preExecuteUrl(bean.getUrl());
            Intent intent = new Intent(holder.itemView.getContext(), WebViewActivity.class);
            intent.putExtra("url", url);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    private String preExecuteUrl(String url){
        StringBuilder res = new StringBuilder();
        res.append("https");
        res.append(url.substring(4));
        return res.toString();
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        MyImageView thumbnail_pic_s;
        MyImageView thumbnail_pic_s02;
        MyImageView thumbnail_pic_s03;
        TextView title;
        TextView date;
        TextView author_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail_pic_s=itemView.findViewById(R.id.thumbnail_pic_s);
            thumbnail_pic_s02=itemView.findViewById(R.id.thumbnail_pic_s02);
            thumbnail_pic_s03=itemView.findViewById(R.id.thumbnail_pic_s03);
            title=itemView.findViewById(R.id.title_news);
            date=itemView.findViewById(R.id.date);
            author_name = itemView.findViewById(R.id.author_name);
        }
    }
}
