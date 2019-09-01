package com.example.qinglv.MainPackage.View.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qinglv.MainPackage.Adapter.ViewPagerFragmentStateAdapter;
import com.example.qinglv.MainPackage.View.activity.SearchActivity;
import com.example.qinglv.util.ZoomOutPageTransformer;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面的碎片
 */

public class FragmentMain extends Fragment {
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        //建立碎片组，可以放在viewPager中
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentSharePath());
        fragmentList.add(new FragmentShareScenic());
        fragmentList.add(new FragmentShareFood());
        fragmentList.add(new FragmentShareTravel());

        //给ViewPager构造适配器，并绑定tabLayout
        ViewPager viewPager = view.findViewById(R.id.view_pager_main);
        viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
        viewPager.setAdapter(new ViewPagerFragmentStateAdapter(getChildFragmentManager(),fragmentList,
                new String[]{"路线","景点","美食","游记"}));
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_main);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




        //搜索框的软键盘回车键设置监听。打开另一个活动展示搜索结果
        final EditText editText = view.findViewById(R.id.editText_fragment_main);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String hint;
                switch(position){
                    case 0:hint = "请输入关键词搜索路线";break;
                    case 1:hint = "请输入关键词搜索景点";break;
                    case 2:hint = "请输入关键词搜索美食";break;
                    case 3:hint = "请输入关键词搜索游记";break;
                    default: hint="";
                }
                hideSoftKeyboard();
                editText.clearFocus();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("query",(v.getText()).toString());
                intent.putExtra("type",position);
                intent.putExtra("hint",hint);
                startActivity(intent);
                return false;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) hideSoftKeyboard();
            }
        });

        return view;
    }

    //收起软键盘
    private void hideSoftKeyboard(){
        if (getActivity()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) inputMethodManager
                    .hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getApplicationWindowToken(),
                            0);
        }
    }
}
