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
    public void refreshList(int articleId, int firstNum, int size , int articleType) {
        iModelComment.getData(articleId, firstNum, size, articleType, new IModelComment.CallBack<Comment>() {
            @Override
            public void onSucceed(List<Comment> list , boolean isMore) {
                if (isAttached()) getView().setComment(list , isMore);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().setToast(errorType);
            }
        });
    }

    @Override
    public void postComment(int articleId, String commentString , int articleType) {
    }
}
