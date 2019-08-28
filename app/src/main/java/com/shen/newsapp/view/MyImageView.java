package com.shen.newsapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int SERVER_ERROR = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    byte[] pic = (byte[]) msg.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(pic,0,pic.length);
                    setImageBitmap(bitmap);
                    break;
                case NETWORK_ERROR:
                    Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
                case SERVER_ERROR:
                    Toast.makeText(getContext(),"服务器发生错误",Toast.LENGTH_SHORT).show();
                    break; }
        }
    };
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(final String path) throws IOException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder url = new StringBuilder();
        url.append("https");
        url.append(path.substring(4));
        Request request = new Request.Builder()
                .url(url.toString())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    byte[] pic = Objects.requireNonNull(response.body()).bytes();
                    Message msg = Message.obtain();
                    msg.obj = pic;
                    msg.what = GET_DATA_SUCCESS;
                    handler.sendMessage(msg);
                }else {
                    handler.sendEmptyMessage(SERVER_ERROR);
                }
            }
        });
    }
}
