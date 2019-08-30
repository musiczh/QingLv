package com.example.qinglv.MainPackage.inter.iApiMvp;

/**
 * 评论的presenter层接口
 */
public interface IPresenterComment {
    void refreshList(int articleId , int firstNum , int size ,int articleType);
    void postComment(int articleId , String commentString ,int articleType);
}
