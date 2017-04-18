package com.wizarpos.cardinfolink.circie_viewpager_imageview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class BannerAdapter extends PagerAdapter {
    List<ImageView> ims=new ArrayList<>();
    public BannerAdapter(List<ImageView> ims){
        this.ims=ims;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(ims.get(position%ims.size()));
        return ims.get(position%ims.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ims.get(position%ims.size()));
    }

}
