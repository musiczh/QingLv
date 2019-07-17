package com.example.qinglv.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
