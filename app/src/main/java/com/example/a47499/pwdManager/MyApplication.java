package com.example.a47499.pwdManager;

import android.app.Application;

import com.example.a47499.pwdManager.db.DBManager;

/**
 * Created by 47499 on 2018/9/22.
 */

public class MyApplication extends Application {
    private DBManager dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        //导入数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
    }
}

