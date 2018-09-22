package com.example.a47499.pwdManager.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.example.a47499.pwdManager.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;


//主界面
public class MainActivity extends AppCompatActivity  {
    private ContentFragment contentFragment;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        setActionBar();
    }



    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //使用菜单填充器获取menu下的菜单资源文件
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //获取搜索的菜单组件
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        //设置搜索的事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                List<PwdModel> models = new ArrayList<>();
                models.add(new PwdModel("ssss"+query));
                contentFragment.myListViewAdapter.updateLV(models);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
