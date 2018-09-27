package com.example.a47499.pwdManager.fragment;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a47499.pwdManager.MyApplication;
import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.adapter.MyListViewAdapter;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.example.a47499.pwdManager.db.MySQLiteOpenHelper;
import com.example.a47499.pwdManager.utils.AnimatorUtil;
import com.example.a47499.pwdManager.view.MyDialog;
import com.example.a47499.pwdManager.utils.PinyinComparator;
import com.example.a47499.pwdManager.weight.SideBar;

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
    private MyApplication app;
    private MySQLiteOpenHelper dbHelper;
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

    private int mTouchShop;//最小滑动距离
    protected float mFirstY;//触摸下去的位置
    protected float mCurrentY;//滑动时Y的位置
    protected int direction;//判断是否上滑或者下滑的标志

    protected boolean mShow;//判断是否执行了上滑动画
    private Animator mAnimator;//动画属性

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        contentListView = getActivity().findViewById(R.id.contentListView);
        PinyinComparator pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) getActivity().findViewById(R.id.sidrbar);
        dialog = (TextView) getActivity().findViewById(R.id.dialog);
        floatingActionButton = getActivity().findViewById(R.id.fab);

        contentListView.setOnTouchListener(new View.OnTouchListener() {//listview的触摸事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();//按下时获取位置
                        break;

                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();//得到滑动的位置
                        if (mCurrentY - mFirstY > mTouchShop) {//滑动的位置减去按下的位置大于最小滑动距离  则表示向下滑动
                            direction = 0;//down
                        } else if (mFirstY - mCurrentY > mTouchShop) {//反之向上滑动
                            direction = 1;//up
                        }

                        if (direction == 1) {//判断如果是向上滑动 则执行向上滑动的动画
                            if (mShow) {//判断动画是否执行了  执行了则改变状态
                                //执行往上滑动的动画
                                AnimatorUtil.hideFab(floatingActionButton);
                                mShow = !mShow;
                            }
                        } else if (direction == 0) {//判断如果是向下滑动 则执行向下滑动的动画
                            if (!mShow) {//判断动画是否执行了  执行了则改变状态
                                //执行往下滑动的动画
                                AnimatorUtil.showFab(floatingActionButton);
                                mShow = !mShow;
                            }
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        break;

                }
                return false;
            }
        });
//        mainLayout=getActivity().findViewById(R.id.mainLayout);
        app = ((MyApplication) getActivity().getApplication());
        dbHelper = app.getDbHelper();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.getInstance(getContext()).show();
            }
        });
        MyApplication app = (MyApplication) getActivity().getApplication();
        MySQLiteOpenHelper dbHelper = app.getDbHelper();
        List<PwdModel> models = (List<PwdModel>) dbHelper.selectList(app.getPwdTableName(), "");
        myListViewAdapter = new MyListViewAdapter(getContext(), models);
        app.setAdapter(myListViewAdapter);
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

//        mImageView.setBackgroundColor(Color.RED);
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


