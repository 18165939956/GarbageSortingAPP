package com.example.garbagesortingapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class looperpagerAdapter extends PagerAdapter {

    /*private List<Integer> mColors = new ArrayList<>();*/
    private List<Integer> mPics = new ArrayList<>();

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        int realPosition = position % mPics.size();
        ImageView imageView = new ImageView(container.getContext());
        /*imageView.setBackgroundColor(mColors.get(position));*/
        imageView.setImageResource(mPics.get(realPosition));
        container.addView(imageView);
        return imageView;
    }

    //获得轮播图的长度—————————————————————————————————————————————————————————————————————————————————
    public int getDataResultSizse(){
        if (mPics != null){
            return mPics.size();
        }
        return 0;
    }
    //获得轮播图的长度—————————————————————————————————————————————————————————————————————————————————

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (mPics != null)
        {return Integer.MAX_VALUE;}
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<Integer> sColors) {
        this.mPics = sColors;
    }
}

