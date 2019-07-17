package com.example.qinglv.View;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinglv.Adapter.SectionStatePagerAdapter;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentMain extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentMainShare());
        fragmentList.add(new FragmentMainPersonal());

        ViewPager viewPager = view.findViewById(R.id.view_pager_main);
        viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
        viewPager.setAdapter(new SectionStatePagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),fragmentList));
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_main);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
