package com.performancedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.bonree.sdk.proto.PBSDKTransfer;
//import com.bonree.sdk.proto.PBSDKTransfer;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        Button netButton = (Button) findViewById(R.id.net_button);
        Button socketButton = (Button) findViewById(R.id.socket_button);
        netButton.setOnClickListener(this);
        socketButton.setOnClickListener(this);
        Utils.sleepTime(30);
        Log.d("<--", "net time:" + (startTime - System.currentTimeMillis()));
    }

    /**
     * 从网络获取json数据,(String byte[})
     * @param path
     * @return
     */

    public String getJsonByInternet(String path){
        try {
            URL url = new URL(path.trim());
            String host = "";
            host = url.getHost();
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("ProtoType", "json");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            urlConnection.setRequestProperty("Host", host);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(30 * 1000);
            urlConnection.connect();
            Log.d("<==","code:"+urlConnection.getResponseCode());
            //得到输入流
            InputStream is =urlConnection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while(-1 != (len = is.read(buffer))){
                baos.write(buffer,0,len);
                baos.flush();
            }
            return baos.toString("utf-8");
        }  catch (Exception e) {
            Log.d("<==",e.toString());
            e.printStackTrace();
        }
        return null;
    }


    public void getJsonByVolley(String path){
        RequestQueue mQueue = Volley.newRequestQueue(NetActivity.this);
        StringRequest stringRequest = new StringRequest(path,
             new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     Log.d("<==", "Success:"+response.length());
                 }
             }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("<==", error.getMessage(), error);
                }
            });
        mQueue.add(stringRequest);
    }

    public void getJsonByOkhttp(String path){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Accept-Encoding", "gzip")
        .build();
        final Request request = new Request.Builder()
                .url(path)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("<==", e.toString());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Log.d("<==", "code:" + response.code());
                Log.d("<==", response.body().toString());
            }
        });
    }

    public void getResponseBySocket(String path,int port){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(path,port), 5000);
            //获取输入输出流
            OutputStream ou = socket.getOutputStream();
            Log.d("<==", "Socket success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("<==", "Socket Failed:"+e.toString());
        }
    }

    private static void startHttpUrlConnection(String url){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            Log.d("<==","start HttpUrlConnection:"+System.currentTimeMillis());
            connection = (HttpURLConnection) new URL(url).openConnection();
            Log.d("<==","end HttpUrlConnection:"+System.currentTimeMillis());
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = reader.readLine()) != null) {
                builder.append(responseLine + "\r\n");
            }
            Log.d("<==","startHttpUrlConnection success");
        } catch (Throwable throwable) {
            Log.d("<==",throwable.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.net_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        startHttpUrlConnection("https://static.oschina.net/uploads/user/405/811064_50.jpg?t=1472625620000");
                        startHttpUrlConnection("http://123.123.123.123:80");
//                        Log.d("<==","start str:"+System.currentTimeMillis());
//                        String str = "";
//                        JSONArray jsonArray = new JSONArray();
//
//                        for(int i = 0;i<9999;i++){
//                            Log.d("<==","i:"+i);
//                            jsonArray.put(i+"https://static.oschina.net/uploads/user/405/811064_50.jpg?t=1472625620000");
//                        }
//                        try {
//                            Log.d("<==","start:"+System.currentTimeMillis());
//                            jsonArray.toString();
//                            Log.d("<==","end:"+System.currentTimeMillis());
//                        } catch (Exception e) {
//                            Log.d("<==","Exception:"+e.toString());
//                            e.printStackTrace();
//                        }
                    }
                }).start();

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            String serversUrl = "http://122.11.47.210:9999/upload/sdk/?key=a58a966b-b7c3-47b8-b9d0-a4d87abf5d86_863361025699374";
//                            String str = "aaaaaaaaaaaaaaaaaaaaaaaaa";
//                            byte[] datatoSend = MessageGZip.compress(str.getBytes());
//                            UploadUtil.httpUrlConnetionGet(serversUrl,datatoSend);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
////                            String serversUrl = "http://122.11.47.210:9999/upload/sdk/?key=a58a966b-b7c3-47b8-b9d0-a4d87abf5d86_863361025699374";
//                            String serversUrl = "https://www.baidu.com";
//                            PBSDKTransfer.SDKRequest.Builder builder = PBSDKTransfer.SDKRequest.newBuilder();
//                            PBSDKTransfer.SDKProtoHeader.Builder headerBuilder = PBSDKTransfer.SDKProtoHeader
//                                    .newBuilder();
//                            headerBuilder.setAppid("a58a966b-b7c3-47b8-b9d0-a4d87abf5d86");
//                            headerBuilder.setAppVersion("6.1.6");
//                            headerBuilder.setDeviceId("863361025699374");
//                            headerBuilder.setDeviceIp(2005480870);
//                            headerBuilder.setSdkVersion("2.2.10.2");
//                            builder.setHeader(headerBuilder);
//                            byte[] datatoSend = MessageGZip.compress(builder.build().toByteArray());
//                            UploadUtil.httpUrlConnetionGetPB(serversUrl,datatoSend);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();


                break;

            case R.id.socket_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getResponseBySocket("14.215.177.39",80);
                    }
                }).start();
                break;
        }
    }
}
