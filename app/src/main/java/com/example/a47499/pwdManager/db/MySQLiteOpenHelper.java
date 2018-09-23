package com.example.a47499.pwdManager.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a47499.pwdManager.bean.PwdModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 47499 on 2018/9/22.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static Integer Version = 1;


    //在SQLiteOpenHelper的子类当中，必须有该构造函数
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
        //必须通过super调用父类当中的构造函数
        super(context, name, factory, version);
    }
    //参数说明
    //context:上下文对象
    //name:数据库名称
    //param:factory
    //version:当前数据库的版本，值必须是整数并且是递增的状态

    public MySQLiteOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }


    public MySQLiteOpenHelper(Context context, String name) {
        this(context, name, Version);
    }

    //当数据库创建的时候被调用
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("创建数据库和表");
        //创建了数据库并创建一个叫records的表
        //SQLite数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        String sql = "create table pwd(id INTEGER primary key Autoincrement,name varchar(200),pwd varchar(200),projectName varchar(200)," +
                "projectLetters varchar(200),userName varchar(200) )";
        //execSQL用于执行SQL语句
        //完成数据库的创建
        db.execSQL(sql);
        //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
    }

    //数据库升级时调用
    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade（）方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("更新数据库版本为:" + newVersion);
    }

    public void update(PwdModel pwdModel, String tableName) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        // 调用update方法修改数据库
        sqliteDatabase.update(tableName, pwdModel.toContentValues(), "id=?", new String[]{String.valueOf(pwdModel.getId())});
        //关闭数据库
        sqliteDatabase.close();
    }

    public void delete(PwdModel pwdModel, String tableName) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        //删除数据
        sqliteDatabase.delete(tableName, "id=?", new String[]{String.valueOf(pwdModel.getId())});
        //关闭数据库
        sqliteDatabase.close();
    }

    public void add(PwdModel pwdModel, String tableName) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        // 调用insert()方法将数据插入到数据库当中
        sqliteDatabase.insert(tableName, null, pwdModel.toContentValues());
        //关闭数据库
        sqliteDatabase.close();
    }

    public Object selectList(String tableName, String condition) {
        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        // 调用SQLiteDatabase对象的query方法进行查询
        // 返回一个Cursor对象：由数据库查询返回的结果集对象
        Cursor cursor = sqliteDatabase.query(tableName, new String[]{"id",
                "name","pwd","projectName","userName","projectLetters"}, "projectName like ?", new String[]{"%" + condition + "%"}, null, null, null);
        List<PwdModel> list = new ArrayList<>();
        //将光标移动到下一行，从而判断该结果集是否还有下一条数据
        //如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            PwdModel pwdModel = new PwdModel(cursor);
            list.add(pwdModel);
            //输出查询结果
            System.out.println("查询到的数据是:" + pwdModel.toString());

        }
        //关闭数据库
        sqliteDatabase.close();
        return list;
    }
}
