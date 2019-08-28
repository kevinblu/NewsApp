package com.shen.newsapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shen.newsapp.MyApp;

@Database(entities = {NewsItem.class},version = 1,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    private static volatile NewsDatabase INSTANCE;

    public NewsDatabase() {
    }

    public static NewsDatabase getInstance(){
        if (INSTANCE == null){
            synchronized (NewsDatabase.class){
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(MyApp.getmContext(),NewsDatabase.class,"newsItem.db").build();
            }
        }
        return INSTANCE;
    }

    public abstract NewsItemDao newsItemDao();
}
