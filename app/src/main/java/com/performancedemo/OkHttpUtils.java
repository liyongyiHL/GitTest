package com.performancedemo;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bonree-lidong on 2017/10/27.
 */
public class OkHttpUtils {

    public static void getResponseByOkhttp(String serversUrl) throws MalformedURLException {

        URL url = new URL(serversUrl);
        // 1.创建OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        // 2.创建请求参数，注意，此处有多种方式
        RequestBody requestBody = new FormBody.Builder()
                .add("Content-Type",
                     "application/x-www-form-urlencoded;charset=UTF-8")
                .add("Accept-Encoding", "gzip")
                .add("Host", url.getHost())
                .add("brkey",
                     "4:2:a95529ce-86e3-4956-ad66-ce20791d81ba_1508494710564")
                .build();

        // 3.创建请求request
        Request request = new Request.Builder()
                .url(serversUrl)
                .post(requestBody)
                .build();


        // 4.发起请求，此处使用的是异步请求，按需要选择同步或异步
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("BRSDK-Agent", e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("BRSDK-Agent", response.body().string());
                    }
                });
    }
}
