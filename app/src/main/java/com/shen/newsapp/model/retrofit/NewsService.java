package com.shen.newsapp.model.retrofit;

import com.shen.newsapp.model.bean.NewsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    /***
     * 这是一个retrofit的请求接口，之后通过retrofit.create(NewsModel.class)生成代理对象
     * 然后获取到对应的Response<NewsBean> response，然后根据这个response里的值，赋值给对应的控件
     */
    @GET("index")
    Call<NewsBean> getNews(@Query("type") String type, @Query("key") String key);
}
