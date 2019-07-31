package com.example.qinglv.AddPackage.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.qinglv.R;



import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * GalleyFinal的图片加载器Glide
 */
public class GlideImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {


        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);

        Glide.with(activity)
                .load("file://" + path)
                .placeholder(defaultDrawable)                              //设置资源加载过程中的占位Drawable
                .error(defaultDrawable)                                    //设置load失败时显示的Drawable
                .override(width, height)                                   //重新设置Target的宽高值
                .diskCacheStrategy(DiskCacheStrategy.NONE)                 //不缓存到SD卡
                .skipMemoryCache(true)                                     //跳过内存缓存
                .centerCrop()
                .apply(options)
                .into(imageView);


    }

    @Override
    public void clearMemoryCache() {

    }
}

