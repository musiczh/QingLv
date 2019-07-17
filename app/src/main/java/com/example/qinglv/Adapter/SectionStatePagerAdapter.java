package com.example.qinglv.Adapter;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 首页viewPager对应碎片的适配器
 * 主要增加对应TabLayout标题
 */

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles = {"共享","我的"};
    private List<Fragment> list;


    public SectionStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        list = fragmentList;
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
        return titles[position];
    }
}
