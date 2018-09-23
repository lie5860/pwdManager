package com.example.a47499.pwdManager.utils;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.a47499.pwdManager.view.MyBehavior;

/**
 * Created by 47499 on 2018/9/23.
 */

public class AnimatorUtil {
    private static AccelerateDecelerateInterpolator LINEAR_INTERRPLATOR =new AccelerateDecelerateInterpolator();
    public static void showFab(View view,MyBehavior.AnimateListener ... listener){
        if (listener.length!=0){
            view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .setListener(listener[0])
                    .start();
        }else {
            view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .start();
        }
        view.setVisibility(View.VISIBLE);

    }
    public static void hideFab(View view, MyBehavior.AnimateListener ... listener){
        if (listener.length!=0){
            view.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .setListener(listener[0])
                    .start();
        }else {
            view.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(600)
                    .setInterpolator(LINEAR_INTERRPLATOR)
                    .start();
        }
        view.setVisibility(View.GONE);
    }
}