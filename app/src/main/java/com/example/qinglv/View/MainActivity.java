package com.example.qinglv.View;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.qinglv.R;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;          //底部导航按钮组

    private FragmentMain fragmentMain;
    private FragmentMessage fragmentMessage;
    private FragmentAdd fragmentAdd;
    private FragmentMy fragmentMy;
    private FragmentShop fragmentShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化界面数据
        initView();
        //设置界面逻辑
        start();
    }

    //初始化界面数据
    private void initView(){
        //设置底部导航栏图标的规格为60dp
        setImgSize(R.id.radio_button_main,R.drawable.selector_main,60);
        setImgSize(R.id.radio_button_shop,R.drawable.selector_shop,60);
        setImgSize(R.id.radio_button_message,R.drawable.selector_message,60);
        setImgSize(R.id.radio_button_my,R.drawable.selector_my,60);
        setImgSize(R.id.radio_button_add,R.drawable.img_add,80);

        //创建碎片实例
        fragmentAdd = new FragmentAdd();
        fragmentMain = new FragmentMain();
        fragmentMessage = new FragmentMessage();
        fragmentShop = new FragmentShop();
        fragmentMy = new FragmentMy();

        //获取底部导航栏实例
        radioGroup = findViewById(R.id.radio_group_main);

        //首次进入直接进入主页
        changeFragment(fragmentMain);
    }

    //设置界面逻辑
    private void start(){


        //给底部按钮组设置监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_main :
                        changeFragment(fragmentMain);
                        break;
                    case R.id.radio_button_add :
                        changeFragment(fragmentAdd);
                        break;
                    case R.id.radio_button_my :
                        changeFragment(fragmentMy);
                        break;
                    case R.id.radio_button_shop :
                        changeFragment(fragmentShop);
                        break;
                    case R.id.radio_button_message :
                        changeFragment(fragmentMessage);
                        break;

                }
            }
        });
    }


    //设置底部导航栏的图标大小，传入两个参数，一个是图标id，一个是selector的id
    private void setImgSize(int buttonId,int selectorId,int size){
        RadioButton radioButton =findViewById(buttonId);
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(selectorId);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, size, size);
        radioButton.setCompoundDrawables(null,drawable_news,null,null);
    }


    //传入一个碎片实例来更换碎片
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_main,fragment);
        transaction.commit();

    }


}
