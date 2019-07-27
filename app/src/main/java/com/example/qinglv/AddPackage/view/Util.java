package com.example.qinglv.AddPackage.view;


import android.content.Context;
import android.graphics.Color;

import com.example.qinglv.R;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

public class Util {


    /**
     * GalleryFinal 初始化配置
     * @param context
     */
    public static void initGalleryFinal(Context context){
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0xFF,0x57,0x22))
                .setTitleBarIconColor(Color.BLACK)
                .setTitleBarTextColor(Color.BLACK)
                .setFabNornalColor(Color.BLACK)
                .setFabPressedColor(Color.BLUE)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                .setIconBack(R.mipmap.ic_action_previous_item)
                .setIconRotate(R.mipmap.ic_action_repeat)
                .setIconCrop(R.mipmap.ic_action_crop)
                .setIconCamera(R.mipmap.ic_action_camera)
                .build();
        //配置功能
       FunctionConfig functionConfig= new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(false)
                .setEnableCrop(false)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .setMutiSelectMaxSize(9)
                .build();


        //配置imageloader
        ImageLoader imageLoader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(context,imageLoader, theme)
                .setFunctionConfig(functionConfig)
                .build();

        GalleryFinal.init(coreConfig);

    }

}
