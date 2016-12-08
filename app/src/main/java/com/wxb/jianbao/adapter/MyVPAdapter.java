package com.wxb.jianbao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * Created by ti on 2016/11/29.
 */

public class MyVPAdapter extends PagerAdapter {

    ArrayList<SimpleDraweeView> list;
    Context context;

    public MyVPAdapter(ArrayList<SimpleDraweeView> list, Context context){

        this.list=list;
        this.context=context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        ViewGroup group = (ViewGroup) list.get(position%list.size()).getParent();

        if (group != null) {
            group.removeView(list.get(position));
        }

        ((ViewPager) container).addView(list.get(position % list.size()));


//        container.addView(list.get(position));
        list.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent=new Intent(context, PhotoViewD.class);

//                context.startActivity(intent);


            }
        });

        return list.get(position % list.size());
//        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
