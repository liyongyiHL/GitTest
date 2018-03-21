package com.performancedemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bonree.agent.android.harvest.Statistics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private long mStartTime;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Json();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStartTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button userButton = (Button) findViewById(R.id.user_crash);
        Button jsonButton = (Button) findViewById(R.id.json_init);
        userButton.setOnClickListener(this);
        jsonButton.setOnClickListener(this);
//        Utils.sleepTime(10);
        Log.d("NBSAgent", "statistics onCreate:" + (System.currentTimeMillis()- mStartTime));

    }

    @Override
    protected void onStart() {
        long startTime = System.currentTimeMillis();
        super.onStart();
        Utils.sleepTime(300);
        Log.d("NBSAgent", "statistics onStart:" + (System.currentTimeMillis() - startTime));
    }


    @Override
    protected void onResume() {
        long startTime = System.currentTimeMillis();
        super.onResume();
        Utils.sleepTime(300);
//        mHandler.sendEmptyMessageDelayed(0,200);
        Log.d("NBSAgent", "statistics onResume:" + (System.currentTimeMillis() - startTime));
    }


    public void Json(){
        try {
            JSONObject jsonObject = new JSONObject();
            for(int i = 0;i<99999;i++){
                jsonObject.put(""+i,i);
            }
            long startTime = System.currentTimeMillis();
            jsonObject.toString();
            Log.d("NBSAgent","json time:"+(System.currentTimeMillis()-startTime));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String  getSystemProperty(String propName){
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return line;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_crash:
                String exceptionName = "myexceptionName";
                String causedBy = "mycausedBy";
                String errordump = "java.lang.ArithmeticException:divide by zero\r\n" +
                        "at com.performancedemo.MainActivity.onClick(MainActivity.java:128)\r\n" +
                        "at android.view.View.performClick(View.java:4446)\r\n" +
                        "at android.view.View$PerformClick.run(View.java:18485)\r\n" +
                        "at android.os.Handler.handleCallback(Handler.java:733)\r\n" +
                        "at android.os.Handler.dispatchMessage(Handler.java:95)\r\n" +
                        "at android.os.Looper.loop(Looper.java:136)\r\n" +
                        "at android.app.ActivityThread.main(ActivityThread.java:5290)\r\n" +
                        "at java.lang.reflect.Method.invokeNative(Native Method)\r\n" +
                        "at java.lang.reflect.Method.invoke(Method.java:515)\r\n" +
                        "at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:859)\r\n";
//                Statistics.setUserException(exceptionName,causedBy,errordump);
                break;

            case R.id.json_init:
//                Log.d("NBSAgent","json time:"+(System.currentTimeMillis()-mStartTime));
//                try {
//                JSONObject jsonObject = new JSONObject();
//                    for(int i = 0;i<49999;i++){
//                        jsonObject.put(""+i,i);
//                    }
//                    long startTime = System.currentTimeMillis();
//                    jsonObject.toString();
//                    Log.d("NBSAgent","json time:"+(System.currentTimeMillis()-startTime));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                String property = getSystemProperty("ro.build.id");
                Log.d("<==","property:"+property);
                break;

        }
    }
}
