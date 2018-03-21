package com.performancedemo;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class InteractActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private int m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DatabaseHelper databaseHelper = new DatabaseHelper(InteractActivity.this);
        mDb = databaseHelper.getWritableDatabase();
        for (int i = 0; i< 200 ; i++){
            setValue();
        }
        mDb.close();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog();
            return false;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }

    public void setValue(){
        m++;
        mDb.execSQL("insert into person(name, age) values('炸', "+m+")");
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InteractActivity.this);
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
}
