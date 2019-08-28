package com.shen.newsapp.model;

import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;

import com.shen.newsapp.MyApp;
import com.shen.newsapp.database.NewsDatabase;
import com.shen.newsapp.database.NewsItem;
import com.shen.newsapp.database.NewsItemDao;
import com.shen.newsapp.model.bean.NewsBean;
import com.shen.newsapp.model.listener.NewsListener;
import com.shen.newsapp.model.retrofit.NewsService;
import com.shen.newsapp.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 在这里我试着实现一下网络请求的方法，
 */
public class NewsModelImpl implements NewsModel {
    final String key = "d7fccade5cf82029dea702c74505e039";
    public static NewsBean newsBeanResponse;
    static List<NewsBean> list = new ArrayList<>();
    List<NewsItem> newsItems = new ArrayList<>();

    @Override
    public void saveAllNews(String newsSource, NewsView newsView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService service= retrofit.create(NewsService.class);
        Call<NewsBean> call = service.getNews(newsSource,key);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //子线程回的数据，子线程去发，不然只能是空对象
                if (response.body().getResult()!=null) {

                    List<NewsBean.ResultBean.DataBean> list = response.body().getResult().getData();
                    for (int i = 0; i < list.size(); i++){
                        newsItems.add(new NewsItem(list.get(i).getTitle(),list.get(i).getDate(),
                                list.get(i).getAuthor_name(),list.get(i).getUrl(),list.get(i).getThumbnail_pic_s()));
                    }
//                    saveAllNews(newsItems);
                    newsView.showAllNews(response.body());
                }else {
                    newsView.showError();
                }

            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                Log.d("网络请求","失败");
            }
        });
    }

    private void saveAllNews(List<NewsItem> newsItems) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsDatabase db = NewsDatabase.getInstance();
                db.newsItemDao().insertAll(newsItems);
                Log.d("database","db save data");
            }
        }).start();
    }

    @Override
    public void loadAllNews(NewsListener listener){
        listener.onComplete(newsBeanResponse);
    }


}
