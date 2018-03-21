package com.performancedemo;

/**
 * Created by bonree-lidong on 2017/11/17.
 */
public class Utils {

    public static void sleepTime(long value){
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
