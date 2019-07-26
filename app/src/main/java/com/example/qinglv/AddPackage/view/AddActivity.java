package com.example.qinglv.AddPackage.view;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinglv.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private static  final String TAG = "AddActivity";
    private static  final int CUT_PICTURE = 1;
    private static  final int SHOW_PICTURE = 2;

    private ImageButton mAddPcitureBtn;
    private PopupWindow mPopWindow;
    private TextView photoTv;
    private TextView photographTv;
    private TextView cancelTv;
    private ImageView picture;

    private Uri  imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //解决拍照抛出的FileUriExposedException异常
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        //初始化界面
        initalView();
    }



    public void initalView(){
        mAddPcitureBtn = findViewById(R.id.add_picture_button);
        picture = findViewById(R.id.output_imageView);
        mAddPcitureBtn.setOnClickListener(this);
    }

    //弹出菜单选项
    public void showPopupWindow(){
        //设置contentView
        View contentView = LayoutInflater.from(AddActivity.this).inflate(R.layout.photo_popuplayout,null);
        mPopWindow = new PopupWindow(contentView, ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击事件
        photoTv = contentView.findViewById(R.id.pop_photo);
        photographTv = contentView.findViewById(R.id.pop_photograph);
        cancelTv = contentView.findViewById(R.id.pop_cancel);
        photoTv.setOnClickListener(this);
        photographTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);
        mPopWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);

        //显示contentView
        View rootView = LayoutInflater.from(AddActivity.this).inflate(R.layout.activity_add,null);
        //窗口动画
        mPopWindow.setAnimationStyle(R.style.contextPhotoAnim);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);

    }


    //开始拍照
    public void startPhotograph(){
       //创建File对象，用于存储拍照后的图片，将此图片存储在sd卡的根目录下
        File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");

        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        //将File对象转换成uri对象
        // uri标识者图片的地址
        imageUri = Uri.fromFile(outputImage);
        //隐式调用相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //拍下的照片会被输出到output_image.jpg中去
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,SHOW_PICTURE);


        mPopWindow.dismiss();

    }

    //调用图库
    public void chooseFromAlbum(){
        File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");

        try {
            if(outputImage.exists()){
                outputImage.delete();
              }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,CUT_PICTURE);
        mPopWindow.dismiss();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       switch (requestCode){
           case CUT_PICTURE:
               if(resultCode == RESULT_OK){
                   Log.d(TAG,"裁剪图片的if内");
                   //启动裁剪程序
                   Intent intent = new Intent("com.android.camera.action.CROP");
                   Log.d(TAG,"intent"+intent);
                   intent.setDataAndType(data.getData(),"image/*");
                   intent.putExtra("scale",true);
                   intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                   startActivityForResult(intent,SHOW_PICTURE);

               }
               Log.d(TAG,"裁剪图片的if外");
               break;
           case SHOW_PICTURE:
               Log.d(TAG,"显示图片");
               if(resultCode == RESULT_OK){
                   try{
                       //将output_image.jpg对象解析成Bitmap对象
                      Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                      //这里显示图片操作
                       picture.setImageBitmap(bitmap);

                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
               }
               break;
           default:
               break;
       }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_picture_button:
                //弹出拍照选项
                showPopupWindow();
                Log.d(TAG,"点击了增加图片");
                break;
            case R.id.pop_photo:

                if(ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //没有权限就申请
                    ActivityCompat.requestPermissions(AddActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    //有权限就打开相册
                    chooseFromAlbum();
                }
                Log.d(TAG,"点击了相册");
                break;
            case R.id.pop_photograph:
                //拍照
                startPhotograph();
                Log.d(TAG,"点击了拍照");
                break;
            case R.id.pop_cancel:
                //取消
                mPopWindow.dismiss();
                break;

        }
    }
}
