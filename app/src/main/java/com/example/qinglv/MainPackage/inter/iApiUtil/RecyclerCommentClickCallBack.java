package com.example.qinglv.MainPackage.inter.iApiUtil;

public interface RecyclerCommentClickCallBack {
    void clickHead(int UserId);
    void clickStar(int commentId);
    void clickContent(int commentId , int userId);
    boolean isStar(int commentId);
}
