package com.example.qinglv.MainPackage.inter.iApiUtil;

import android.media.Image;
import android.widget.ImageView;

public interface RecyclerCommentClickCallBack {
    void clickHead(int UserId);
    void clickStar(int commentId , int position);
    void clickContent(int commentId , int userId);
    void isStar(int commentId  , int position);
}
