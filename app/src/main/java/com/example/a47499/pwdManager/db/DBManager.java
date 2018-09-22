package com.example.a47499.pwdManager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by 47499 on 2018/9/22.
 */

public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "pwd.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.example.a47499.pwdManager";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置

    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {

        if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
            //初始化数据库
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                null);
        return db;

    }
}
