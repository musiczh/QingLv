package com.example.qinglv.MainPackage.inter.iApiMvp;

import com.example.qinglv.MainPackage.Entity.Comment;

import java.util.List;

public interface IModelComment  {
    public void getData(int articleId , int firstNum , int size , int articleType, CallBack<Comment> callBack );
    public void postComment(int articleId , String commentString, int articleType, CallBack<String> callBack );
    interface CallBack<T>{
        void onSucceed(List<T> list , boolean isMare);
        void onError(String errorType);
    }
}
