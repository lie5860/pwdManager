package com.example.a47499.pwdManager.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a47499.pwdManager.MyApplication;
import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.adapter.MyListViewAdapter;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.example.a47499.pwdManager.db.MySQLiteOpenHelper;
import com.example.a47499.pwdManager.utils.PinyinComparator;
import com.example.a47499.pwdManager.weight.SideBar;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Collections;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    private ListView contentListView;
    private SideBar sideBar;
    private TextView dialog;
    public MyListViewAdapter myListViewAdapter;
    private FloatingActionButton floatingActionButton;
//    private LinearLayout mainLayout;

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        contentListView = getActivity().findViewById(R.id.contentListView);
        PinyinComparator pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) getActivity().findViewById(R.id.sidrbar);
        dialog = (TextView) getActivity().findViewById(R.id.dialog);
        floatingActionButton = getActivity().findViewById(R.id.fab);
//        mainLayout=getActivity().findViewById(R.id.mainLayout);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "add时间", Toast.LENGTH_SHORT).show();
                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());

                dialogBuilder
                        .withTitle("Modal Dialog")
                        .withMessage("This is a modal Dialog.")
                        .show();
            }
        });
        MyApplication app = (MyApplication) getActivity().getApplication();
        MySQLiteOpenHelper dbHelper = app.getDbHelper();
        List<PwdModel> models = (List<PwdModel>) dbHelper.selectList(app.getPwdTableName(), "");
        myListViewAdapter = new MyListViewAdapter(getContext(), models);
        Collections.sort(models, pinyinComparator);
        contentListView.setAdapter(myListViewAdapter);
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                if (s.equals("※")) {
                    contentListView.setSelection(0);
                }
                int position = myListViewAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    contentListView.setSelection(position);//加上头部的headerview
                }
            }
        });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setBackgroundColor(Color.RED);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}


