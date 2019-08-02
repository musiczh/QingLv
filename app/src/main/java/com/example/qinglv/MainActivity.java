package com.example.qinglv;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.qinglv.AddPackage.view.AddActivity;

import com.example.qinglv.MainPackage.View.fragment.FragmentMain;
import com.example.qinglv.MessagePackage.FragmentMessage;
import com.example.qinglv.UserPackage.View.FragmentMy;
import com.example.qinglv.ShopPackage.FragmentShop;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 主函数，主界面
 */

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;          //底部导航按钮组

    private FragmentMain fragmentMain;
    private FragmentMessage fragmentMessage;
    private FragmentMy fragmentMy;
    private FragmentShop fragmentShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加腾讯bugLy
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化BugLy
        CrashReport.initCrashReport(context, "9ad928519d", true, strategy);


        //初始化界面数据
        initView();
        //设置界面逻辑
        start();
    }

    //初始化界面数据
    private void initView(){
        //设置底部导航栏图标的规格为60dp
        setImgSize(R.id.radio_button_main,R.drawable.selector_main,55);
        setImgSize(R.id.radio_button_shop,R.drawable.selector_shop,55);
        setImgSize(R.id.radio_button_message,R.drawable.selector_message,55);
        setImgSize(R.id.radio_button_my,R.drawable.selector_my,55);
        setImgSize(R.id.radio_button_add,R.drawable.img_add,90);


        //获取底部导航栏实例
        radioGroup = findViewById(R.id.radio_group_main);

        //首次进入直接进入主页
        if (fragmentMain == null){ fragmentMain = new FragmentMain(); }
        changeFragment(fragmentMain);
    }

    //设置界面逻辑
    private void start(){


        //给底部按钮组设置监听
        //点击先隐藏所有碎片，再显示点击的碎片
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideFragment(transaction);    //隐藏所有的碎片
                //判断碎片是否存在，存在则直接展示，不存在则新建实例后再展示
                switch (checkedId){
                    case R.id.radio_button_main :
                        transaction.show(fragmentMain);
                        break;
                    case R.id.radio_button_add :
;
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.radio_button_my :
                        if (fragmentMy == null){
                            fragmentMy = new FragmentMy();
                            transaction.add(R.id.layout_frame_main,fragmentMy);
                        }else {
                            transaction.show(fragmentMy);
                        }
                        break;
                    case R.id.radio_button_shop :
                        if (fragmentShop == null){
                            fragmentShop = new FragmentShop();
                            transaction.add(R.id.layout_frame_main,fragmentShop);
                        }else {
                            transaction.show(fragmentShop);
                        }
                        //changeFragment(fragmentShop);
//                        Intent intent2 = new Intent(MainActivity.this, ArticleActivity.class);
//                        startActivity(intent2);
                        break;
                    case R.id.radio_button_message :
                        if (fragmentMessage == null){
                            fragmentMessage = new FragmentMessage();
                            transaction.add(R.id.layout_frame_main,fragmentMessage);
                        }else {
                            transaction.show(fragmentMessage);
                        }
                        break;

                }
                //最后提交所有的修改
                transaction.commit();
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

    //隐藏所有的碎片
    private void hideFragment(FragmentTransaction transaction){
        if (fragmentMain!=null) transaction.hide(fragmentMain);
        if (fragmentMessage!=null) transaction.hide(fragmentMessage);
        if (fragmentMy!=null) transaction.hide(fragmentMy);
        if (fragmentShop!=null) transaction.hide(fragmentShop);
    }


    //传入一个碎片实例来更换碎片
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_main,fragment);
        transaction.commit();

    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
