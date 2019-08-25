package com.example.qinglv.MainPackage.Model;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.View.activity.CommentActivity;
import com.example.qinglv.MainPackage.bean.PostCommentBackBean;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelComment;
import com.example.qinglv.MainPackage.inter.iApiService.PathCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.PathPostCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicPostCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelPostCommentApiService;
import com.example.qinglv.util.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements IModelComment {
    @Override
    public void getData(int articleId, int firstNum,final int size, int articleType, final CallBack<Comment> callBack ) {
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
                public void onResponse(@NotNull Call<PreviewBean<Comment>> call,
                                       @NotNull Response<PreviewBean<Comment>> response) {
                    if (response.body()!=null) {
                        List<Comment> list = response.body().getMessage();
                        boolean isMore = list.size()>=size;
                        callBack.onSucceed(list,isMore);
                    }else{
                        callBack.onError("好像出了点小问题");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<PreviewBean<Comment>> call, @NotNull Throwable t) {
                    callBack.onError("好像出了点小问题");
                }
            });
        }
        else callBack.onError("获取不到文章类型");

    }

    @Override
    public void postComment(int articleId, String commentString, int articleType, final CallBackPost callBack ) {
        Call<PostCommentBackBean> call = null;
        boolean hasType = true;
        switch (articleType){
            case CommentActivity.PATH:
                call = RetrofitManager.getInstance().createRs(PathPostCommentApiService.class)
                        .postPath(articleId,commentString);
                break;
            case CommentActivity.SCENIC:
                call = RetrofitManager.getInstance().createRs(ScenicPostCommentApiService.class)
                        .postScenic(articleId,commentString);
                break;
            case CommentActivity.TRAVEL:
                call = RetrofitManager.getInstance().createRs(TravelPostCommentApiService.class)
                        .postTravel(articleId,commentString);
                break;
            default:hasType = false;
        }
        if (hasType) {
            call.enqueue(new Callback<PostCommentBackBean>() {
                @Override
                public void onResponse(@NotNull Call<PostCommentBackBean> call,
                                       @NotNull Response<PostCommentBackBean> response) {
                    if (response.body()!=null){
                        String result = response.body().getResult();
                        if (result.equals("success")) callBack.onSucceed(result);
                        else callBack.onError("提交失败");
                    }else{
                        callBack.onError("好像出了一点小问题");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<PostCommentBackBean> call, @NotNull Throwable t) {
                    callBack.onError("评论提交出了一点小问题");
                }
            });
        }
        else callBack.onError("获取不到文章类型");

    }
}
