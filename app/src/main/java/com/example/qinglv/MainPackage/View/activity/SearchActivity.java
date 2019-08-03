package com.example.qinglv.MainPackage.View.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.qinglv.MainPackage.View.fragment.FragmentShareFood;
import com.example.qinglv.MainPackage.View.fragment.FragmentShareScenic;
import com.example.qinglv.MainPackage.View.fragment.FragmentShareTravel;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;
import com.example.qinglv.R;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {
    private IViewPreview fragment;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        //根据传入来的键值判断要开启哪一个碎片
        switch (intent.getIntExtra("type",1)){
            case 1: fragment = new FragmentShareFood();break;
            case 2: fragment = new FragmentShareFood();break;
            case 3: fragment = new FragmentShareScenic();break;
            case 4: fragment = new FragmentShareTravel();break;
        }


        //初始化toolBar
        Toolbar toolbar = findViewById(R.id.toolbar_search);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //建立碎片并替换
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_search,(Fragment)fragment);
        fragmentTransaction.commit();

        //初始化搜索框
        SearchView searchView = findViewById(R.id.searchView_search);
        searchView.onActionViewExpanded();
        searchView.setQuery(intent.getStringExtra("query"),true); //预输入
        searchView.setQueryHint(intent.getStringExtra("hint")); //提示内容
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fragment.setQuery(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s == null){
                    fragment.setQuery(null);
                }
                return false;
            }
        });



    }

    //顶部toolBar返回按钮的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
