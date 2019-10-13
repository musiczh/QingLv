package com.example.qinglv.MainPackage.Model;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.View.activity.CommentActivity;
import com.example.qinglv.MainPackage.bean.CommentPostBean;
import com.example.qinglv.MainPackage.bean.BackBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiService.IsStarApiService;
import com.example.qinglv.MainPackage.inter.iApiService.PathCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.PathPostCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicPostCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.SetStarApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelCommentApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelPostCommentApiService;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import com.example.qinglv.util.RetrofitManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements IModelComment {
    private boolean isCommentStar = false;
    @Override
    public void getData(int articleId,final int firstNum,final int size, int articleType, final CallBack<Comment> callBack ) {
        /*Call<PreviewBean<Comment>> call = null;
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
                    callBack.onError("该文章暂时没有评论");
                }
            });
        }
        else callBack.onError("获取不到文章类型");*/


        Call<ResponseBody> call = null;
        //判断是哪种文章的评论
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
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                    if (response.body() != null) {
                        try {
                            boolean isMore = true;
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            if (!jsonObject.getString("result").equals("failed")){
                                JSONArray jsonArray = jsonObject.getJSONArray("message");
                                Gson gson = new Gson();
                                List<Comment> list = gson.fromJson(jsonArray.toString(),
                                        new TypeToken<List<Comment>>(){}.getType());
                                if (list.size()<size) isMore = false;
                                if (list.isEmpty()){
                                    callBack.onError("好像出了点小问题(json异常)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                                }else {
                                    callBack.onSucceed(list, isMore);
                                }
                            }else{
                                if (firstNum == 0) callBack.onError(jsonObject.getString("message") , RecyclerViewAdapterWrapper.NO_COMMENT);
                                else callBack.onError(jsonObject.getString("message") , RecyclerViewAdapterWrapper.NO_MORE);
                            }
                        } catch (JSONException e) {
                            callBack.onError("好像出了点小问题(json异常)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        callBack.onError("好像出了点小问题(body=null)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                    }

                }

                @Override
                public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                    callBack.onError("获取不到文章类型" , RecyclerViewAdapterWrapper.NO_INTERNET);
                }
            });
        }
        else callBack.onError("获取不到文章类型" , RecyclerViewAdapterWrapper.NO_INTERNET);
    }

    @Override
    public void postComment(int articleId, String commentString, int articleType, final CallBackPost callBack ) {
        Call<BackBean> call = null;
        CommentPostBean commentPostBean = new CommentPostBean();
        commentPostBean.setArticleId(articleId);
        commentPostBean.setContent(commentString);
        boolean hasType = true;
        switch (articleType){
            case CommentActivity.PATH:
                call = RetrofitManager.getInstance().createRs(PathPostCommentApiService.class)
                        .postPath(commentPostBean);
                break;
            case CommentActivity.SCENIC:

                call = RetrofitManager.getInstance().createRs(ScenicPostCommentApiService.class)
                        .postScenic(commentPostBean);
                break;
            case CommentActivity.TRAVEL:
                call = RetrofitManager.getInstance().createRs(TravelPostCommentApiService.class)
                        .postTravel(commentPostBean);
                break;
            default:hasType = false;
        }
        if (hasType) {
            call.enqueue(new Callback<BackBean>() {
                @Override
                public void onResponse(@NotNull Call<BackBean> call,
                                       @NotNull Response<BackBean> response) {
                    if (response.body()!=null){
                        String result = response.body().getResult();
                        if (result.equals("success")) callBack.onSucceed(result);
                        else callBack.onError("提交失败");
                    }else{
                        callBack.onError("好像出了一点小问题");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                    callBack.onError("评论提交出了一点小问题");
                }
            });
        }
        else callBack.onError("获取不到文章类型");

    }

    @Override
    public void setCommentStar(int commentId, int typeId, final IModelDetail.CallBackStar callBackStar) {
        RetrofitManager.getInstance().createRs(SetStarApiService.class)
                .setStar(commentId , typeId)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            if (response.body().getResult().equals("success")) callBackStar.onSucceed(true);
                            else callBackStar.onError("你已经点过赞啦");

                        }else{
                            callBackStar.onError("出了点小问题 （comment body == null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                        callBackStar.onError("出了点小问题 （comment onFailure）");
                    }
                });
    }

    @Override
    public void isCommentStar(int commentId, int typeId, final IModelDetail.CallBackStar callBackStar) {
        RetrofitManager.getInstance().createRs(IsStarApiService.class)
                .isStar(commentId , typeId)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            if (response.body().getResult().equals("success")) {
                                callBackStar.onSucceed(true);
                            }

                        }else{
                            callBackStar.onError("出了点小问题（comment body == null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                            callBackStar.onError("出了点小问题（comment onFailure）");
                    }
                });

    }


}
