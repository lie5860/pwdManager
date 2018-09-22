package com.example.a47499.pwdManager.utils;

import com.example.a47499.pwdManager.bean.PwdModel;

import java.util.Comparator;

/**
 * Created by 47499 on 2018/9/22.
 */

public class PinyinComparator implements Comparator<PwdModel> {

    public int compare(PwdModel o1, PwdModel o2) {
        if (o1.getProjectLetters().equals("@")
                || o2.getProjectLetters().equals("#")) {
            return -1;
        } else if (o1.getProjectLetters().equals("#")
                || o2.getProjectLetters().equals("@")) {
            return 1;
        } else {
            return o1.getProjectLetters().compareTo(o2.getProjectLetters());
        }
    }

}