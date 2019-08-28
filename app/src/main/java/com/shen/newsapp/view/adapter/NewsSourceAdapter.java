package com.shen.newsapp.view.adapter;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shen.newsapp.R;
import com.shen.newsapp.presenter.NewsPresenter;

import java.util.List;
import java.util.Map;

public class NewsSourceAdapter extends RecyclerView.Adapter<NewsSourceAdapter.ViewHolder> {
    List<String> list;
    NewsPresenter presenter1;
    Map<String,String> map = new ArrayMap<>();
    public NewsSourceAdapter(List<String> list, NewsPresenter presenter) {
        this.list = list;
        presenter1 = presenter;
        initMap();
    }

    private void initMap() {
        map.put("头条","toutiao");
        map.put("社会","shehui");
        map.put("国内","guonei");
        map.put("国际","guoji");
        map.put("娱乐","yule");
        map.put("体育","tiyu");
        map.put("军事","junshi");
        map.put("科技","keji");
        map.put("财经","caijing");
        map.put("时尚","shishang");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_source,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.news_source.setOnClickListener(view1 -> {
            int pos = holder.getAdapterPosition();
            String source = list.get(pos);
            presenter1.fetchNews(map.get(source));
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.news_source.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends NewsViewAdapter.ViewHolder{
        TextView news_source;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_source = itemView.findViewById(R.id.news_source_tab);
        }
    }
}
