package com.example.a47499.pwdManager;

import android.app.Application;

import com.example.a47499.pwdManager.adapter.MyListViewAdapter;
import com.example.a47499.pwdManager.db.MySQLiteOpenHelper;

/**
 * Created by 47499 on 2018/9/22.
 */

public class MyApplication extends Application {
    private MySQLiteOpenHelper dbHelper;
    private String pwdTableName = "pwd";
    private static MyApplication instance;
    private MyListViewAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();
        //导入数据库
        dbHelper = new MySQLiteOpenHelper(this, "test");
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }


    public MySQLiteOpenHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(MySQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public String getPwdTableName() {
        return pwdTableName;
    }

    public void setPwdTableName(String pwdTableName) {
        this.pwdTableName = pwdTableName;
    }



    public MyListViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MyListViewAdapter adapter) {
        this.adapter = adapter;
    }
}

