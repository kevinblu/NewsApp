package com.shen.newsapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NewsItemDao {
    @Query("SELECT * FROM newsItem")
    List<NewsItem> getAll();
    @Query("SELECT * FROM newsitem WHERE id IN (:newsIds)")
    List<NewsItem> loadByIds(int[] newsIds);
    @Insert
    void insertAll(List<NewsItem> newsItems);
    @Delete
    void delete(NewsItem newsItem);
}
