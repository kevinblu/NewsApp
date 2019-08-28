package com.shen.newsapp;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shen.newsapp.model.bean.NewsBean;
import com.shen.newsapp.presenter.NewsPresenter;
import com.shen.newsapp.view.NewsView;
import com.shen.newsapp.view.adapter.NewsViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NewsView {

    NewsPresenter presenter = new NewsPresenter(this);
    @BindView(R.id.recyclerViewForTitle)
    RecyclerView recyclerViewForTitle;
    @BindView(R.id.recyclerViewForNews)
    RecyclerView recyclerViewForNews;

    private static String[] PERMISSION_INTERNET={
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
//            if (ActivityCompat.checkSelfPermission(
//                    MainActivity.this,Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(MainActivity.this,PERMISSION_INTERNET, 1);
//            }
//        }
        ButterKnife.bind(this);
        presenter.setAllTabs(recyclerViewForTitle,presenter);
        presenter.fetchNews("top");
    }



    @Override
    public void showAllNews(NewsBean bean) {
        NewsViewAdapter adapter = new NewsViewAdapter(bean);
        recyclerViewForNews.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewForNews.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this,"超过每日可允许请求次数,明天再试啦",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (permissions[0].equals(Manifest.permission.INTERNET)){
                Toast.makeText(this,"用户同意使用权限",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"用户不同意使用权限",Toast.LENGTH_SHORT).show();
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.INTERNET)){
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("该共享需要赋予联网权限，否则将无法正常工作")
                        .setPositiveButton("确认", (dialogInterface, i) -> finish())
                        .setNegativeButton("取消", (dialogInterface, i) -> finish()).create();
                dialog.show();
                return;
            }
        }
        finish();
    }
}
