package com.jpr.volleyhttp;

import android.os.Bundle;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jpr.volleyhttp.http.Volley;
import com.jpr.volleyhttp.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    String url = "http://apis.juhe.cn/cook/query.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        User user = new User();

        for (int i = 0; i < 50; i++) {
            Log.d("jiao" , "i ===" + i);
            Volley.sendRequest(user, url, LoginResponse.class, new IDataListener<LoginResponse>() {


                @Override
                public void onSuccess(LoginResponse loginResponse) {
                    Log.i("jiao", loginResponse.toString());
                }

                @Override
                public void onFail() {
                    Log.d("jiao", "获取失败");
                }
            });
        }
    }
}
