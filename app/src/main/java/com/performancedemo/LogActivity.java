package com.performancedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Utils.sleepTime(30);
        Button logButton = (Button) findViewById(R.id.log_button);
        logButton.setOnClickListener(this);
        Log.d("<--","log time:"+(startTime-System.currentTimeMillis()));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static String getLogcatInfo(int timeout) {
        String info = "";
        try {
            ExecutorService exec = Executors.newFixedThreadPool(1);
            Callable<String> call = new Callable<String>() {
                public String call() throws Exception {
                    Process logcatProcess = null;
                    Process clearProcess = null;
                    StringBuffer sb = new StringBuffer();
                    try {
                        ArrayList<String> cmdLine = new ArrayList<String>();
                        cmdLine.add("logcat");
                        cmdLine.add("-d");
                        cmdLine.add("-v");
                        cmdLine.add("time");
                        ArrayList<String> clearLog = new ArrayList<String>();
                        clearLog.add("logcat");
                        clearLog.add("-c");
                        logcatProcess = Runtime.getRuntime().exec(
                                cmdLine.toArray(new String[cmdLine.size()]));
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(
                                        logcatProcess.getInputStream()));
                        clearProcess = Runtime.getRuntime().exec(
                                clearLog.toArray(new String[clearLog.size()]));
                        String line1 = null;
                        int count = 0;
                        while ((line1 = bufferedReader.readLine()) != null || ++count<3000) {
                            Log.d("<==","line:"+line1);
                            sb.append(line1 + "\n");
                        }
                        bufferedReader.close();
                    } finally {
                        try {
                            if (logcatProcess != null) {
                                logcatProcess.exitValue();
                            }
                        } catch (IllegalThreadStateException e) {
                            logcatProcess.destroy();

                        }
                        try {
                            if (clearProcess != null) {
                                clearProcess.exitValue();
                            }
                        } catch (IllegalThreadStateException e) {
                            clearProcess.destroy();
                        }
                    }
                    return sb.toString();
                }
            };
            Future<String> future = exec.submit(call);
            future.get(timeout, TimeUnit.MILLISECONDS);
            info = future.get();
            exec.shutdown();
        } catch (Throwable e) {
            Log.d("<==",e.toString());
        }
        return info;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.log_button:
                getLogcatInfo(1000);
                break;
        }
    }
}
