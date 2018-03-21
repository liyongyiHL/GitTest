package com.performancedemo;

import android.net.TrafficStats;
import android.os.*;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TrafficActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        Button trafficButton = (Button) findViewById(R.id.traffic);
        trafficButton.setOnClickListener(this);
        Log.e("<--","traffic:"+ TrafficStats.getUidRxBytes(Process.myUid()));
    }

    /**
     * 通过uid查询文件夹中的数据
     * @param localUid
     * @return
     */
    private Long getTotalBytesManual(int localUid) {
        Log.e("<--", "localUid:" + localUid);
        File dir = new File("/proc/uid_stat/");
        String[] children = dir.list();
        if (!Arrays.asList(children).contains(String.valueOf(localUid))) {
            return 0L;
        }
        File uidFileDir = new File("/proc/uid_stat/" + String.valueOf(localUid));
        File uidActualFileReceived = new File(uidFileDir, "tcp_rcv");
        File uidActualFileSent = new File(uidFileDir, "tcp_snd");
        String textReceived = "0";
        String textSent = "0";
        try {
            BufferedReader brReceived = new BufferedReader(new FileReader(uidActualFileReceived));
            BufferedReader brSent = new BufferedReader(new FileReader(uidActualFileSent));
            String receivedLine;
            String sentLine;
            if ((receivedLine = brReceived.readLine()) != null) {
                textReceived = receivedLine;
                Log.e("<--", "receivedLine:" + receivedLine);
            }
            if ((sentLine = brSent.readLine()) != null) {
                textSent = sentLine;
                Log.e("<--", "sentLine:" + sentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("<--", e.toString());
        }
        return Long.valueOf(textReceived).longValue() + Long.valueOf(textSent).longValue();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.traffic:
                Long totalBytesManual = getTotalBytesManual(Process.myUid());
                Log.e("<--","totalBytesManual:"+totalBytesManual);
                break;
        }
    }
}
