package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Model.CommentModel;
import com.example.qinglv.MainPackage.View.activity.CommentActivity;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewComment;
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
            public void onError(String errorType) {
                if (isAttached()) getView().setToast(errorType);
            }
        });
    }

    @Override
    public void postComment(final int articleId, String commentString ,final int articleType) {
        iModelComment.postComment(articleId, commentString, articleType, new IModelComment.CallBackPost() {
            @Override
            public void onSucceed(String result) {
                if (isAttached()) getView().setToast(result);
                refreshList(articleId,0,10,articleType,true);

            }

            @Override
            public void onError(String error) {
                if (isAttached()) getView().setToast(error);
            }
        });
    }
}
