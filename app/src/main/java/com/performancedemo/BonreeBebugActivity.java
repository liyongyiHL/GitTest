package com.performancedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class BonreeBebugActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonree_bebug);
        Button memroyButton = (Button) findViewById(R.id.memory);
        Button appcpuButton = (Button) findViewById(R.id.appcpu);
        Button syscpuButton = (Button) findViewById(R.id.syscpu);
        Button signalButton = (Button) findViewById(R.id.signal);
        Button trafficButton = (Button) findViewById(R.id.traffic);
        Button batteryButton = (Button) findViewById(R.id.battery);
        Button fpsButton = (Button) findViewById(R.id.fps);
        memroyButton.setOnClickListener(this);
        appcpuButton.setOnClickListener(this);
        syscpuButton.setOnClickListener(this);
        signalButton.setOnClickListener(this);
        trafficButton.setOnClickListener(this);
        batteryButton.setOnClickListener(this);
        fpsButton.setOnClickListener(this);
        Utils.sleepTime(30);
        Log.d("<--", "BonreeDebug time:" + (startTime - System.currentTimeMillis()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.memory:
//                double memory = BonreeDebug.getMemory();
//                Log.d("==>","memory:"+memory);
                break;
            case R.id.appcpu:
//                double appCpu = BonreeDebug.getAppCpu();
//                Log.d("==>","appCpu:"+appCpu);
                break;
            case R.id.syscpu:
//                double systemCpu = BonreeDebug.getSystemCpu();
//                Log.d("==>","systemCpu:"+systemCpu);
                break;
            case R.id.signal:
//                int signal = BonreeDebug.getSignal();
//                Log.d("==>","signal:"+signal);
                break;
            case R.id.traffic:
//                long netTrafficUsage = BonreeDebug.getNetTrafficUsage();
//                Log.d("==>","netTrafficUsage:"+netTrafficUsage);
                break;
            case R.id.battery:
//                int batteryUsage = BonreeDebug.getBatteryUsage();
//                Log.d("==>","batteryUsage:"+batteryUsage);
                break;
            case R.id.fps:
//                int fps = BonreeDebug.getFps();
//                Log.d("==>","fps:"+fps);
                for (int m = 0; m < 999999; m++) {
                    for (int n = 0; n < 999999; n++) {
                        int z = m;
                    }
                }
                break;
        }
    }
}
