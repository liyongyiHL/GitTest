package com.performancedemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bonree.agent.android.Bonree;
import com.networkbench.agent.impl.NBSAppAgent;
import com.performancedemo.dfzq.AuthWebActivity;
import com.tencent.bugly.crashreport.CrashReport;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 23) {
            getManifest();
        } else {
//           NBSAppAgent.setLicenseKey("1df727b9979a4f76b6485889f49dd8ed").start(
//                                    this.getApplicationContext());

//            NBSAppAgent.setLicenseKey("4f43d36ed18642d081fe096c2a0bdd57").start(
//                    this.getApplicationContext());//WUHAIYAN

//            Bonree.withApplicationToken("4a9990c2-57ac-4ca9-be86-1def7a2eb68d").
//                    withConfigUrl("https://sdkconfig.reedoun.com/config/").
//                    start(this);//LD-0825行为

            Bonree.withApplicationToken("d1559759-94ff-493a-aa2a-a696b33018b1").
                    withConfigUrl("https://sdkconfig.reedoun.com/config/").
                    start(this);//LD-0825行为
        }

//        CrashReport.initCrashReport(getApplicationContext(), "900045594", true);

        Log.d("<--", "main tid:" + android.os.Process.myTid());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logButton = (Button) findViewById(R.id.logcat);
        Button netButton = (Button) findViewById(R.id.net);
        Button jsonButton = (Button) findViewById(R.id.json);
        Button webviewButton = (Button) findViewById(R.id.webview);
        Button crashButton = (Button) findViewById(R.id.crash);
        Button statisticsButton = (Button) findViewById(R.id.statistics);
        Button anrButton = (Button) findViewById(R.id.anr);
        Button bonreeDebugButton = (Button) findViewById(R.id.bonreeDebug);
        Button authWebActivityButton = (Button) findViewById(R.id.authWebActivity);
        Button trafficActivityButton = (Button) findViewById(R.id.trafficActivity);
        logButton.setOnClickListener(this);
        netButton.setOnClickListener(this);
        jsonButton.setOnClickListener(this);
        webviewButton.setOnClickListener(this);
        crashButton.setOnClickListener(this);
        statisticsButton.setOnClickListener(this);
        anrButton.setOnClickListener(this);
        bonreeDebugButton.setOnClickListener(this);
        authWebActivityButton.setOnClickListener(this);
        trafficActivityButton.setOnClickListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void getManifest() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                                              new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                              0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Bonree.withApplicationToken("4a9990c2-57ac-4ca9-be86-1def7a2eb68d").
//                            withConfigUrl("https://sdkconfig.reedoun.com/config/").
//                            start(this);//LD-0825行为
//                    Bonree.withApplicationToken("70afcbde-c1e2-4645-a386-12315e145013")
//                            .withConfigUrl("http://122.11.47.205/config/sdk/")
//                            .start(this); //0818-行为
                    NBSAppAgent.setLicenseKey("1df727b9979a4f76b6485889f49dd8ed").start(
                            this.getApplicationContext());
                }
                return;
            }
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logcat:
                Intent logIntent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(logIntent);
                break;
            case R.id.net:
                Intent netIntent = new Intent(MainActivity.this, NetActivity.class);
                startActivity(netIntent);
                break;
            case R.id.json:
                Intent jsonIntent = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(jsonIntent);
                break;
            case R.id.webview:
                Intent webviewIntent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(webviewIntent);
                break;
            case R.id.statistics:
                Intent statisticsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(statisticsIntent);
                break;
            case R.id.anr:
                for (int m = 0; m < 999999; m++) {
                    for (int n = 0; n < 999999; n++) {
                        int z = m;
                    }
                }
                break;
            case R.id.crash:
                int i = 0;
                int j = 5 / i;
                break;
            case R.id.bonreeDebug:
                Intent bonreeBebugIntent = new Intent(MainActivity.this, BonreeBebugActivity.class);
                startActivity(bonreeBebugIntent);
                break;
            case R.id.authWebActivity:
                Intent authWebActivityIntent = new Intent(MainActivity.this, AuthWebActivity.class);
                startActivity(authWebActivityIntent);
                break;
            case R.id.trafficActivity:
                Intent trafficActivityIntent = new Intent(MainActivity.this, TrafficActivity.class);
                startActivity(trafficActivityIntent);
                break;
        }
    }
}
