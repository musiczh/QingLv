package com.example.qinglv.AddPackage.view;

import android.content.Context;
import android.graphics.Color;

import com.example.qinglv.AddPackage.view.GlideImageLoader;
import com.example.qinglv.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * GalleryFinal 初始化配置的工具类，再Activity内调用时传入上下文
 */

public class InitGalleryFinal {

    public static FunctionConfig functionConfig;

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
        functionConfig= new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(false)
                .setForceCropEdit(true)
                .setMutiSelectMaxSize(9)
                .build();
        initImageLoader(context);
        //配置imageloader

        CoreConfig coreConfig = new CoreConfig.Builder(context,new GlideImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .build();

        GalleryFinal.init(coreConfig);
    }
    private static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());



    }


}
