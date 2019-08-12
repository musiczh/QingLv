package com.example.qinglv.MainPackage.Model;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.View.activity.CommentActivity;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelComment;
import com.example.qinglv.MainPackage.inter.iApiService.PathCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelCommentApiService;
import com.example.qinglv.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements IModelComment {
    @Override
    public void getData(int articleId, int firstNum, int size, int articleType,CallBack<Comment> callBack ) {
        Call<PreviewBean<Comment>> call = null;
        boolean hasType = true;
        switch (articleType){
            case CommentActivity.PATH:
                call = RetrofitManager.getInstance().createRs(PathCommentApiService.class)
                        .getComment(articleId , firstNum , size);
                break;
            case CommentActivity.SCENIC:
                call = RetrofitManager.getInstance().createRs(ScenicCommentApiService.class)
                        .getComment(articleId , firstNum , size);
                break;
            case CommentActivity.TRAVEL:
                call = RetrofitManager.getInstance().createRs(TravelCommentApiService.class)
                        .getComment(articleId , firstNum , size);
                break;
            default:hasType = false;
        }
        if (hasType) {
            call.enqueue(new Callback<PreviewBean<Comment>>() {
                @Override
                public void onResponse(Call<PreviewBean<Comment>> call, Response<PreviewBean<Comment>> response) {

                }

                @Override
                public void onFailure(Call<PreviewBean<Comment>> call, Throwable t) {

                }
            });
        }
        else callBack.onError("获取不到文章类型");

    }

    @Override
    public void postComment(int articleId, String commentString, int articleType, CallBack<String> callBack ) {

    }
}
