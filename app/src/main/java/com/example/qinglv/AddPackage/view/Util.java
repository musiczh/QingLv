package com.example.qinglv.AddPackage.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class Util {

    private static final int TAKE_PHOTO = 3;

  /*  //调用摄像头拍照
    public  void  takePhoto(Activity activity, Uri imageUri){
        //创建File对象，用于存储拍照后的照片
        File outputImage = new File(activity.getExternalCacheDir(),
                "output_image.jpg");

        try {
        if(outputImage.exists()){
            outputImage.delete();
        }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(activity.getBaseContext(),"com.example.qinglv.Util.fileprovier",outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        activity.startActivityForResult(intent,TAKE_PHOTO);
    }*/

}
