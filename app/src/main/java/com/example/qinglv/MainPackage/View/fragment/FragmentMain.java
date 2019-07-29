package com.example.qinglv.MainPackage.View.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinglv.MainPackage.Adapter.SectionsFragmentStateViewPagerAdapter;
import com.example.qinglv.MainPackage.util.ZoomOutPageTransformer;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面的碎片
 */

public class FragmentMain extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        //建立碎片组，可以放在viewPager中
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentSharePath());
        fragmentList.add(new FragmentShareScenic());
        fragmentList.add(new FragmentShareFood());
        fragmentList.add(new FragmentShareTravels());

        //给ViewPager构造适配器，并绑定tabLayout
        ViewPager viewPager = view.findViewById(R.id.view_pager_main);
        //viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
        viewPager.setAdapter(new SectionsFragmentStateViewPagerAdapter(getChildFragmentManager(),fragmentList,
                new String[]{"路线","景点","美食","游记"}));
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_main);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
