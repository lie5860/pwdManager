package com.example.a47499.pwdManager.bean;

import com.example.a47499.pwdManager.utils.CharacterParser;

/**
 * Created by 47499 on 2018/9/22.
 */

public class PwdModel {
    private String userName;
    private String pwd;
    private String projectLetters;
    private String projectNmae;
    private String createTime;
    private String updateTime;
    private int srcId;//图片源

    public PwdModel(String projectNmae) {
        this.projectNmae = projectNmae;
        this.projectLetters = String.valueOf(CharacterParser.cn2FirstSpell(projectNmae).charAt(0));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getProjectNmae() {
        return projectNmae;
    }

    public void setProjectNmae(String projectNmae) {
        this.projectNmae = projectNmae;
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
}
