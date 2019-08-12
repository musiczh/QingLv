package com.example.qinglv.AddPackage.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qinglv.AddPackage.view.activity.AddActivity;
import com.example.qinglv.R;
import com.example.qinglv.AddPackage.view.ShowPopupWindow;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * 相册选择显示的RecyclerView的适配器
 */

public class PhotoListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_CONTRNT = 1001;
    private static final int ITEM_TYPE_BOTTOM = 1002;

    private ImageView ivPhoto;
    private List<PhotoInfo> mList;
    private LayoutInflater mInflater;




    public PhotoListAdapter(Activity activity, List<PhotoInfo> list) {
        this.mList = list;
        this.mInflater = LayoutInflater.from(activity);
    }

    @SuppressLint("ResourceType")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        if (i == ITEM_TYPE_BOTTOM) {
            Log.d("底部View", "i=" + i);
            view = mInflater.inflate(R.layout.recyclerview_footer, viewGroup, false);
            holder = new FooterViewHolder(view);
           setHeight(((FooterViewHolder) holder).footerImage);
        }else {
            view = mInflater.inflate(R.layout.adapter_photo_item,viewGroup,false);
            holder = new ViewHolder(view);
            setHeight(((ViewHolder) holder).imageView);
        }
        return holder;
    }


    //将数据与界面绑定的操作
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.d("测试", "i=" + i + "------" + isBottomView(i));
        if (isBottomView(i)) {       //判断是否是最后一个item
            if (viewHolder instanceof FooterViewHolder) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
                footerViewHolder.footerImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //弹出拍照选项
                        Log.d("footerViewHolder的点击事件","已点击");
                       new ShowPopupWindow().showPopupWindow(AddActivity.mContext);
                    }
                });
            }
        } else {
            final ViewHolder holder = (ViewHolder) viewHolder;
            DisplayImageOptions options = new DisplayImageOptions.Builder().build();
            PhotoInfo photoInfo = mList.get(i);
            ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), holder.imageView, options);   //显示图片

              //单击事件
               holder.imageView.setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {

                   }
               });
               //长按事件
               holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                   @Override
                   public boolean onLongClick(View v) {
                       holder.deletePhotoBtn.setVisibility(View.VISIBLE);
                       return true;
                   }
               });
               //删除图片
            holder.deletePhotoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeList(i);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return getContentItemCount()+1;
    }


    //底部ViewHolder
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        ImageView footerImage;
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            footerImage = itemView.findViewById(R.id.recy_footer_add);
        }
    }

    //自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
       static   ImageView imageView;
        static ImageButton deletePhotoBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_photo_iv);
            deletePhotoBtn = itemView.findViewById(R.id.item_delete_photo);
        }
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (getContentItemCount() == position) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return position;
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
        private void setHeight ( final ImageView imageView){
            Activity currentActivity = AddActivity.getCurrentActivity();
            //取控件当前的布局参数
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            //设置宽度值
            params.width = dip2px(currentActivity, 100);
            //设置高度值
            params.height = dip2px(currentActivity, 100);
            //使设置好的布局参数应用到控件
            imageView.setLayoutParams(params);

//            convertView.setLayoutParams(new RecyclerView.LayoutParams(400, 400));    //设置宽高
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }


        private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    //删除条目
    public void removeList(int position){
        mList.remove(position);//删除数据源,移除集合中当前下标的数据
        notifyItemRemoved(position);//刷新被删除的地方
        notifyItemRangeChanged(position, getItemCount()); //刷新被删除数据，以及其后面的数据
    }
}

