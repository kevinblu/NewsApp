package com.shen.newsapp.view;

import com.shen.newsapp.model.bean.NewsBean;

/**
 * 这里就是所谓的View层接口，
 * 这里可以与之前的model层进行类比
 * model层也是先有一个model的接口，然后有一个实现类，
 * 之后的Presenter层持有的引用就是这个实现类的
 * 这里的view层也行先新建一个接口，之后在对应的activity或者fragment里实现这个接口
 * Presenter层到时候创建构造函数的时候可以把这个View接口传进去，这样就与activity或者fragment解耦了
 */
public interface NewsView {
    void showAllNews(NewsBean bean);

    void showError();
}
