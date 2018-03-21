package com.performancedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bonree.agent.android.instrumentation.JSONObjectInstrumentation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity implements View.OnClickListener {

    String str = "{\n" +
            "    \"h\": {\n" +
            "        \"ai\": \"4a9990c2-57ac-4ca9-be86-1def7a2eb68d\",\n" +
            "        \"di\": {\n" +
            "            \"av\": \"1.0(1)\",\n" +
            "            \"bn\": \"Huawei\",\n" +
            "            \"ch\": \"\",\n" +
            "            \"ci\": \"\",\n" +
            "            \"cm\": \"ARMv7 Processor rev 1 (v7l) \",\n" +
            "            \"di\": \"357458044309879\",\n" +
            "            \"ds\": \"1080*1776\",\n" +
            "            \"pi\": \"\",\n" +
            "            \"l\": \"\",\n" +
            "            \"m\": \"HUAWEI P7-L00\",\n" +
            "            \"ov\": \"4.4.2\",\n" +
            "            \"ir\": true,\n" +
            "            \"tm\": 1894.44\n" +
            "        },\n" +
            "        \"sv\": \"2.2.9.17\",\n" +
            "        \"dip\": 2005480589\n" +
            "    },\n" +
            "    \"udr\": {\n" +
            "        \"d\": [\n" +
            "            {\n" +
            "                \"aar\": [],\n" +
            "                \"ar\": {\n" +
            "                    \"an\": \"com.performancedemo.MainActivity\",\n" +
            "                    \"et\": 4423000,\n" +
            "                    \"lt\": 171,\n" +
            "                    \"st\": 360000\n" +
            "                },\n" +
            "                \"wr\": [],\n" +
            "                \"dci\": [],\n" +
            "                \"dl\": [],\n" +
            "                \"nr\": [\n" +
            "                    {\n" +
            "                        \"nsi\": {\n" +
            "                            \"am\": \"Wifi\",\n" +
            "                            \"nt\": -1,\n" +
            "                            \"s\": 2000\n" +
            "                        },\n" +
            "                        \"ti\": 0,\n" +
            "                        \"li\": 0,\n" +
            "                        \"st\": 0,\n" +
            "                        \"dsi\": 0,\n" +
            "                        \"et\": 0,\n" +
            "                        \"rd\": 0,\n" +
            "                        \"rds\": 0,\n" +
            "                        \"ei\": 0,\n" +
            "                        \"dti\": 0,\n" +
            "                        \"dt\": 0,\n" +
            "                        \"ib\": false,\n" +
            "                        \"rt\": 0,\n" +
            "                        \"rti\": 0,\n" +
            "                        \"ct\": 0,\n" +
            "                        \"se\": 0,\n" +
            "                        \"si\": 0,\n" +
            "                        \"lp\": 0,\n" +
            "                        \"sti\": 0,\n" +
            "                        \"iw\": false,\n" +
            "                        \"tp\": 0\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"s\": [],\n" +
            "                \"si\": \"a03b86e9-c5db-44aa-99f5-c4aa89830fd4\",\n" +
            "                \"mt\": 1505962753017,\n" +
            "                \"cmt\": 1505962748580\n" +
            "            }\n" +
            "        ],\n" +
            "        \"tu\": {\n" +
            "            \"mu\": 0,\n" +
            "            \"wu\": 8343\n" +
            "        },\n" +
            "        \"iat\": true\n" +
            "    }\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        Button jsonButton = (Button) findViewById(R.id.json);
        jsonButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.json:
//                SenderUtil.parseGson(str);
                    try {
                            JSONObject jsonObject =  new JSONObject(str);
                    } catch (JSONException e) {
                            e.printStackTrace();
                    }
                break;
        }
    }
}
