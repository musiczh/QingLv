package com.example.qinglv.AddPackage.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.qinglv.AddPackage.adapter.PhotoListAdapter;
import com.example.qinglv.R;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


/**
 * 用于弹窗拍照的工具类
 */

public class ShowPopupWindow implements View.OnClickListener {

    TextView photoTv;
    TextView photographTv;
    TextView cancelTv;
    PopupWindow mPopWindow;
    private static  final int REQUEST_CODE_GALLERY = 1;  //打开相册
    private static  final int REQUEST_CODE_CAMERA = 2;    //使用拍照



    //弹出菜单选项
    public  void showPopupWindow(Context context){

        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.photo_popuplayout,null);
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
        View rootView = LayoutInflater.from(context).inflate(R.layout.activity_add,null);
        //窗口动画
        mPopWindow.setAnimationStyle(R.style.contextPhotoAnim);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pop_photo:
                Activity currentActivity = AddActivity.getCurrentActivity();
                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //没有权限就申请
                    ActivityCompat.requestPermissions(currentActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Toast.makeText(AddActivity.mContext,"请打开权限后再试",Toast.LENGTH_SHORT).show();
                    mPopWindow.dismiss();
                } else {
                    //有权限就打开相册
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, InitGalleryFinal.functionConfig, mOnHanlderResultCallback);
                    mPopWindow.dismiss();

                }
                break;
            case R.id.pop_photograph:
                //拍照
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA,InitGalleryFinal.functionConfig, mOnHanlderResultCallback);
                mPopWindow.dismiss();

                break;
            case R.id.pop_cancel:
                //取消
                mPopWindow.dismiss();
                break;
        }
    }

        /**
         * 选择图片结果回调
         */
        public  GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                Activity currentActivity = AddActivity.getCurrentActivity();
                if(resultList!=null){
                    if(reqeustCode == REQUEST_CODE_GALLERY){
                        Log.d("showPopupWindow","打开相册后回调"+resultList.size());
                        AddActivity.list=resultList;
                        PhotoListAdapter adapter = new PhotoListAdapter(currentActivity,resultList);
                        AddActivity.mRecyclerView.setAdapter(adapter);
                    }else if(reqeustCode == REQUEST_CODE_CAMERA){
                        Log.d("showPopupWindow","打开相机后回调");
                    }
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {                  //失败时回调
                Toast.makeText(AddActivity.mContext, "选择图片失败"+errorMsg, Toast.LENGTH_SHORT).show();
                Log.d("showPopupWindow","errorMsg"+errorMsg);
            }
        };

    }
