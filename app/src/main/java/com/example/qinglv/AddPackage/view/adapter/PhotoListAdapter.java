package com.example.qinglv.AddPackage.view.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.qinglv.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * 相册选择显示的RecyclerView的适配器
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder>{

    private  ImageView ivPhoto;


    private List<PhotoInfo> mList;
    private LayoutInflater mInflater;
    private int mScreenWidth;

    public PhotoListAdapter(Activity activity, List<PhotoInfo> list) {
        this.mList = list;
        this.mInflater = LayoutInflater.from(activity);
        this.mScreenWidth = DeviceUtils.getScreenPix(activity).widthPixels;
    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        ivPhoto = (ImageView) mInflater.inflate(R.layout.adapter_photo_item, null);
        setHeight(ivPhoto);


        ViewHolder holder = new ViewHolder(ivPhoto);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_add)
                .showImageForEmptyUri(R.mipmap.ic_add)
                .showImageOnLoading(R.mipmap.ic_add).build();


        PhotoInfo photoInfo = mList.get(i);
        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), ivPhoto, options);
        Log.d("适配器","imageView"+i+"-----------------"+photoInfo.getPhotoPath()+"-------------------");


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_photo_image);

        }
    }

    //设置图片的大小
    private void setHeight(final ImageView convertView) {
        convertView.setLayoutParams(new RecyclerView.LayoutParams(400, 400));    //设置宽高
        convertView.setScaleType(ImageView.ScaleType.CENTER_INSIDE  );
    }

}

