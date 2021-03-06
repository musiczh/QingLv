package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Model.CommentModel;
import com.example.qinglv.MainPackage.View.activity.CommentActivity;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewComment;
import com.example.qinglv.MainPackage.inter.iApiUtil.CommentStarCallBack;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import com.example.qinglv.util.RetrofitManager;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

public class CommentPresenter extends BasePresenter<IViewComment> implements IPresenterComment {
    private IModelComment iModelComment = new CommentModel();

    @Override
    public void refreshList(int articleId, int firstNum, int size , int articleType , final boolean isClear) {
        iModelComment.getData(articleId, firstNum, size, articleType, new IModelComment.CallBack<Comment>() {
            @Override
            public void onSucceed(List<Comment> list , boolean isMore) {
                if (isAttached()) getView().setComment(list , isMore , isClear);
            }

            @Override
            public void onError(String errorType , int recyclerViewFootType) {
                if (isAttached()) getView().setRecyclerToast(errorType , recyclerViewFootType);
            }
        });
    }

    @Override
    public void postComment(final int articleId, String commentString ,final int articleType) {
        iModelComment.postComment(articleId, commentString, articleType, new IModelComment.CallBackPost() {
            @Override
            public void onSucceed(String result) {
                if (isAttached()) getView().setRecyclerToast(result , RecyclerViewAdapterWrapper.LOADING);
                refreshList(articleId,0,10,articleType,true);

            }

            @Override
            public void onError(String error) {
                if (isAttached()) getView().setRecyclerToast(error , -1);
            }
        });
    }



    @Override
    public void isCommentStar(int commentId , int commentType ,final CommentStarCallBack commentStarCallBack) {
        iModelComment.isCommentStar(commentId, commentType, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                commentStarCallBack.notifyItem(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().setToast(errorType);
            }
        });
    }



    @Override
    public void setCommentStar(int commentId , int commentType , final CommentStarCallBack commentStarCallBack) {
        iModelComment.setCommentStar(commentId, commentType, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                commentStarCallBack.notifyItem(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().setToast(errorType);
            }
        });
    }
}
