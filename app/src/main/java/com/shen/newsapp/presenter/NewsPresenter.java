package com.shen.newsapp.presenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shen.newsapp.model.NewsModel;
import com.shen.newsapp.model.NewsModelImpl;
import com.shen.newsapp.view.adapter.NewsSourceAdapter;
import com.shen.newsapp.view.NewsView;

import java.util.Arrays;
import java.util.List;

public class NewsPresenter {
    //持有model层引用
    NewsModel newsModel = new NewsModelImpl();
    //持有View层引用
    NewsView newsView;
    //设置一个监听器
    List<String> tabList = Arrays.asList("头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚");

    public void setAllTabs(RecyclerView recyclerView,NewsPresenter presenter){
        NewsSourceAdapter adapter = new NewsSourceAdapter(tabList,presenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    //构造函数，将View接口作为传参，实则是传了一个实现了该接口的activity
    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }
    //获取新闻，也就是我们的业务逻辑
    public void fetchNews(String name){
        //这里其实是一个网络请求，如何在请求完之后才让View层去刷新？
        //这里大概想到了监听器。
        newsModel.saveAllNews(name,newsView);
//        newsModel.loadAllNews(new NewsListener() {
//            @Override
//            public void onComplete(NewsBean sourceRequest) {
//                List<NewsItem> newsItemList = new ArrayList<>();
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        NewsDatabase db = NewsDatabase.getInstance();
////                        newsItemList.addAll(db.newsItemDao().getAll());
////                    }
////                }).start();
////
////
////                while (newsItemList==null){
////                    try {
////                        wait(1000);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                }
//                newsView.showAllNews(sourceRequest);
//            }
//        });
    }

}
