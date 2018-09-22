package com.example.a47499.pwdManager.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.a47499.pwdManager.utils.CharacterParser;

/**
 * Created by 47499 on 2018/9/22.
 */

public class PwdModel extends Object {
    private Integer id;
    private String name;
    private String pwd;
    private String projectLetters;
    private String projectName;
    private String userName;
    private String createTime;
    private String updateTime;
    private int srcId;//图片源

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public PwdModel(String projectName) {
        this.projectName = projectName;
        this.projectLetters = String.valueOf(CharacterParser.cn2FirstSpell(projectName).charAt(0));
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProjectLetters() {
        return projectLetters;
    }

    public void setProjectLetters(String projectLetters) {
        this.projectLetters = projectLetters;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectNmae) {
        this.projectName = projectNmae;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public PwdModel(Cursor cursor) {
        if (cursor.getColumnIndex("id") != -1) {
            this.id = cursor.getInt(cursor.getColumnIndex("id"));
        }
        if (cursor.getColumnIndex("name") != -1) {
            this.name = cursor.getString(cursor.getColumnIndex("name"));
        }
        if (cursor.getColumnIndex("pwd") != -1) {
            this.pwd = cursor.getString(cursor.getColumnIndex("pwd"));
        }
        if (cursor.getColumnIndex("projectName") != -1) {
            this.projectName = cursor.getString(cursor.getColumnIndex("projectName"));
        }
        if (cursor.getColumnIndex("projectLetters") != -1) {
            this.projectLetters = cursor.getString(cursor.getColumnIndex("projectLetters"));
        }
        if (cursor.getColumnIndex("userName") != -1) {
            this.userName = cursor.getString(cursor.getColumnIndex("userName"));
        }
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("pwd", pwd);
        values.put("projectName", projectName);
        values.put("projectLetters", projectLetters);
        values.put("userName", userName);
        return values;
    }

    @Override
    public String toString() {
        return "PwdModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", projectLetters='" + projectLetters + '\'' +
                ", projectName='" + projectName + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", srcId=" + srcId +
                '}';
    }
}
