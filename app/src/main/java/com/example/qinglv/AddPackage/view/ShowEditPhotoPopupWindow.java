package com.example.qinglv.AddPackage.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;



/**
 * 点击RecyclerView子项的图片后出现的弹窗
 */
public class ShowEditPhotoPopupWindow{


    TextView deleteTv;
    TextView cancelTv;
    private PopupWindow mPopWindow;
    private RecyclerClickCallback mClickCallback;


    //弹出菜单选项
    public  void showPopupWindow(Context context, final int position, final List<PhotoInfo> list, final RecyclerClickCallback clickCallback){

        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.edit_popiplayout,null);
        mPopWindow = new PopupWindow(contentView, ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击事件
        deleteTv = contentView.findViewById(R.id.pop_delete);
        cancelTv = contentView.findViewById(R.id.pop_cancel);

        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallback.onClick(position);
                mPopWindow.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        mPopWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);

        //显示contentView
        View rootView = LayoutInflater.from(context).inflate(R.layout.activity_add,null);
        //窗口动画
        mPopWindow.setAnimationStyle(R.style.contextPhotoAnim);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);


    }


}
