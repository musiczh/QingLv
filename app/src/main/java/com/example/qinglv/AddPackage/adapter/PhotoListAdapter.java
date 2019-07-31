package com.example.qinglv.AddPackage.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class PhotoListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_CONTRNT = 1001;
    private static final int ITEM_TYPE_BOTTOM = 1002;

    private ImageView ivPhoto;


    private List<PhotoInfo> mList;
    private LayoutInflater mInflater;
    private int mScreenWidth;

    public PhotoListAdapter(Activity activity, List<PhotoInfo> list) {
        this.mList = list;
        this.mInflater = LayoutInflater.from(activity);
        this.mScreenWidth = DeviceUtils.getScreenPix(activity).widthPixels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        RecyclerView.ViewHolder holder = null;
        View view = null;

        if (i == ITEM_TYPE_BOTTOM) {
            Log.d("底部View", "i=" + i);
//
            view = mInflater.inflate(R.layout.recyclerview_footer, viewGroup, false);
            holder = new FooterViewHolder(view);
        }else {
            ivPhoto = (ImageView) mInflater.inflate(R.layout.adapter_photo_item, null);
            setHeight(ivPhoto);
            holder = new ViewHolder(ivPhoto);
        }

        return holder;
    }


    //将数据与界面绑定的操作
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("测试", "i=" + i + "------" + isBottomView(i));
        if (isBottomView(i)) {       //判断是否是最后一个item

            if (viewHolder instanceof FooterViewHolder) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
                Log.d("向下转型", "成功");

            } else {
                Log.d("向下转型", "失败");
            }

            Log.d("底部View", "viewHolder instanceof FooterViewHolder----" + i);

        } else {

            DisplayImageOptions options = new DisplayImageOptions.Builder().build();
            PhotoInfo photoInfo = mList.get(i);
            ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), ivPhoto, options);

            Log.d("适配器", "imageView" + i + "-----------------" + photoInfo.getPhotoPath() + "-------------------");
        }
    }


    @Override
    public int getItemCount() {
        return getContentItemCount()+1;
    }

    //底部ViewHolder
    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageButton;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.recy_footer_add);
        }
    }

    //自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_photo);

        }
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (getContentItemCount() == position) {
//        底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return position;
//        }
        }
    }

        //内容长度
        public int getContentItemCount() {
            return mList == null ? 0 : mList.size();
        }

        //判断当前item是否是FooterView
        public boolean isBottomView ( int position){
            return position == (getContentItemCount());
        }


        //设置图片的大小
        private void setHeight ( final ImageView convertView){
            convertView.setLayoutParams(new RecyclerView.LayoutParams(400, 400));    //设置宽高
            convertView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }


}

