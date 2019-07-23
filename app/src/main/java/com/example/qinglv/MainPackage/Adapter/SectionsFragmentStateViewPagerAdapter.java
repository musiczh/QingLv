package com.example.qinglv.MainPackage.Adapter;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 1.viewPager对应碎片的适配器
 * 2.主要增加对应TabLayout标题
 * 3.构造器传入三个参数，FragmentManager，fragmentList，和String[],最后一个参数是标题栏的名字
 */

public class SectionsFragmentStateViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<Fragment> list;


    public SectionsFragmentStateViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        list = fragmentList;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
