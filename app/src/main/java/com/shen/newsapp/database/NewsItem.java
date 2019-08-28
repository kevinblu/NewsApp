package com.shen.newsapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "author_name")
    private String author_name;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "pic_url")
    private String thumbnail_pic_s;

    public NewsItem(String title, String date, String author_name, String url, String thumbnail_pic_s) {
        this.title = title;
        this.date = date;
        this.author_name = author_name;
        this.url = url;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
