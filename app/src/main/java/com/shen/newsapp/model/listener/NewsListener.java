package com.shen.newsapp.model.listener;

import com.shen.newsapp.model.bean.NewsBean;

public interface NewsListener {
    void onComplete(NewsBean sourceRequest);
}
