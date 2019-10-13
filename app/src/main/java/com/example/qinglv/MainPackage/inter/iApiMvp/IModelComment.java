package com.example.qinglv.MainPackage.inter.iApiMvp;

import com.example.qinglv.MainPackage.Entity.Comment;

import java.util.List;

public interface IModelComment  {
    public void getData(int articleId , int firstNum , int size , int articleType, CallBack<Comment> callBack );
    public void postComment(int articleId , String commentString, int articleType, CallBackPost callBack );
    public void setCommentStar(int commentId , int typeId , IModelDetail.CallBackStar callBackStar);
    public void isCommentStar(int commentId , int typeId , IModelDetail.CallBackStar callBackStar);
    interface CallBack<T>{
        void onSucceed(List<T> list , boolean isMare);
        void onError(String errorType , int recyclerViewFootType);
    }
    interface CallBackPost{
        void onSucceed(String result);
        void onError(String error);
    }
}
