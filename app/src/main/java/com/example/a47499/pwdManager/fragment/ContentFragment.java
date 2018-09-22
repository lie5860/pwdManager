package com.example.a47499.pwdManager.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.adapter.MyListViewAdapter;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.example.a47499.pwdManager.utils.PinyinComparator;
import com.example.a47499.pwdManager.weight.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    private ListView contentListView;
    private SideBar sideBar;
    private TextView dialog;
    public MyListViewAdapter myListViewAdapter;
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
//        mainLayout=getActivity().findViewById(R.id.mainLayout);


        List<PwdModel> models = new ArrayList<>();
        models.add(new PwdModel("ssss"));
        models.add(new PwdModel("刷刷刷"));
        models.add(new PwdModel("ccc"));
        models.add(new PwdModel("次奥"));
        models.add(new PwdModel("aaaa"));
        models.add(new PwdModel("安安"));
        models.add(new PwdModel("ssss"));
        models.add(new PwdModel("刷刷刷"));
        models.add(new PwdModel("ccc"));
        models.add(new PwdModel("次奥"));
        models.add(new PwdModel("aaaa"));
        models.add(new PwdModel("安安"));
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


    /*
    Color transition method.
     */
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
//        mImageView.setBackgroundColor(Color.RED);
        mImageView.setImageResource(res);
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


