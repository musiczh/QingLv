package com.example.qinglv.MainPackage.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qinglv.R;

import java.util.List;

public class ViewPagerAdapterImage extends PagerAdapter {
    private List<String> mList;
    private Context mContext;

    public ViewPagerAdapterImage(List<String> list , Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.image_view_pager , container);
        ImageView imageView = view.findViewById(R.id.imageView_detail_travel_viewPager);
        Glide.with(mContext).load(mList.get(position)).into(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
