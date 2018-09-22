package com.example.a47499.pwdManager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

/**
 * Created by 47499 on 2018/9/22.
 */

public
class MyListViewAdapter extends BaseAdapter implements SectionIndexer {
    private List<PwdModel> list = null;
    private Context context;

    public MyListViewAdapter(Context context, List<PwdModel> list) {
        this.context=context;
        this.list=list;
    }
    public void updateLV(List<PwdModel> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final PwdModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_pwd_selecter, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
//            viewHolder.ivSrc=(ImageView)view.findViewById(R.id.item_iv);

            viewHolder.swipeLayout = (SwipeLayout) view.findViewById(R.id.sample1);
            viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            View starBottView = viewHolder.swipeLayout.findViewById(R.id.starbott);
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper_2));
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Top, starBottView);
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Bottom, starBottView);
            viewHolder.swipeLayout.addRevealListener(R.id.delete, new SwipeLayout.OnRevealListener() {
                @Override
                public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {

                }
            });

            viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Click on surface", Toast.LENGTH_SHORT).show();
                    Log.d(MyActivity.class.getName(), "click on surface");
                }
            });
            viewHolder.swipeLayout.getSurfaceView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "longClick on surface", Toast.LENGTH_SHORT).show();
                    Log.d(MyActivity.class.getName(), "longClick on surface");
                    return true;
                }
            });
            viewHolder.swipeLayout.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Star", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.swipeLayout.findViewById(R.id.trash2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Trash Bin", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.swipeLayout.findViewById(R.id.magnifier2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Magnifier", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.swipeLayout.addRevealListener(R.id.starbott, new SwipeLayout.OnRevealListener() {
                @Override
                public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
                    View star = child.findViewById(R.id.star);
                    float d = child.getHeight() / 2 - star.getHeight() / 2;
                    ViewHelper.setTranslationY(star, d * fraction);
                    ViewHelper.setScaleX(star, fraction + 0.6f);
                    ViewHelper.setScaleY(star, fraction + 0.6f);
                }
            });


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(section)){
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getProjectLetters());
        }else{
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        viewHolder.tvTitle.setText(this.list.get(position).getProjectNmae());
//        viewHolder.ivSrc.setImageResource(list.get(position).getSrcId());

        return view;
    }
    final static class ViewHolder {
        TextView tvLetter;
        ImageView ivSrc;
        TextView tvTitle;
        SwipeLayout swipeLayout;
    }
    @Override
    public Object[] getSections() {
        return null;
    }
    /**
     * 根据首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getProjectLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getProjectLetters().charAt(0);
    }

    //获取顶部textview的文字
    public String getHeaderText(int position){
        if (position<0||position>=list.size()){
            return "热门城市";
        }
        return list.get(position).getProjectLetters();
    }
}
