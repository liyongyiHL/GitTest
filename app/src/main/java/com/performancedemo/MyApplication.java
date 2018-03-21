package com.performancedemo;

import android.app.Application;

import com.bonree.agent.android.Bonree;

/**
 * Created by bonree-lidong on 2017/11/30.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Bonree.withApplicationToken("2a4ad433-84a7-4297-93a4-9208bee474f3").
//                withConfigUrl("https://sdkconfig.reedoun.com/config/").
//                start(this);//LD-0825行为
    }
}
