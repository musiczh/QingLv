package com.example.qinglv.AddPackage.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.qinglv.R;



import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * GalleyFinal的图片加载器Glide
 */
public class GlideImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        Glide.with(activity)
                .load("file://" + path)
                .placeholder(defaultDrawable)                              //设置资源加载过程中的占位Drawable
                .error(defaultDrawable)                                    //设置load失败时显示的Drawable
                .override(width, height)                                   //重新设置Target的宽高值
                .diskCacheStrategy(DiskCacheStrategy.NONE)                 //不缓存到SD卡
                .skipMemoryCache(true)                                     //跳过内存缓存
                //.centerCrop()
                .into(new ImageViewTarget<GlideDrawable>(imageView) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(Request request) {
                        imageView.setTag(R.id.adapter_item_tag_key,request);
                    }

                    @Override
                    public Request getRequest() {
                        return (Request) imageView.getTag(R.id.adapter_item_tag_key);
                    }
                });
    }

    @Override
    public void clearMemoryCache() {
    }
}
