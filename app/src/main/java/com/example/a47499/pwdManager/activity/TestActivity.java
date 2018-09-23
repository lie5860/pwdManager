package com.example.a47499.pwdManager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.view.NumLockPanel;

/**
 * Created by 47499 on 2018/9/23.
 */

public class TestActivity extends AppCompatActivity {

    private NumLockPanel mNumLockPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mNumLockPanel = (NumLockPanel) findViewById(R.id.num_lock);
        mNumLockPanel.setInputListener(new NumLockPanel.InputListener() {
            @Override
            public void inputFinish(String result) {
                //此处result即为输入结果
                if (result.equals("0000")) {
                    Explode explode = new Explode();
                    explode.setDuration(500);

                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(TestActivity.this);
                    Intent i2 = new Intent(TestActivity.this, MainActivity.class);
                    startActivity(i2, oc2.toBundle());
                } else {
                    //错误效果示例
                    mNumLockPanel.showErrorStatus();
                }
            }
        });
    }
}
