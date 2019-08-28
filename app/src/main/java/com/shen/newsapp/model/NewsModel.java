package com.shen.newsapp.model;

import com.shen.newsapp.model.listener.NewsListener;
import com.shen.newsapp.view.NewsView;

/**
 * 这是MVP架构的一个比较通用的接口写法，
 * model接口里面定义获取数据、存储数据等方法
 * model的实现类modelImpl则具体实现它们，
 * 这样写的好处是在有多个需要实现类似功能的时候，
 * 不用再写一个接口，而且可以检查其他类里面的方法是否定义方法正确
 */
public interface NewsModel {

    void saveAllNews(String newsSource, NewsView newsView);

    void loadAllNews(NewsListener listener);
}
