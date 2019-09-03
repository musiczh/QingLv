package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Entity.TravelDetail;
import com.example.qinglv.MainPackage.bean.BackBean;
import com.example.qinglv.MainPackage.bean.DetailBean;
import com.example.qinglv.MainPackage.bean.StarPostBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiService.IsCollectionApiService;
import com.example.qinglv.MainPackage.inter.iApiService.IsStarApiService;
import com.example.qinglv.MainPackage.inter.iApiService.SetCollectionApiService;
import com.example.qinglv.MainPackage.inter.iApiService.SetStarApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelDetailApiService;
import com.example.qinglv.util.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.qinglv.util.StaticQuality.TYPE_TRAVEL;
import static com.example.qinglv.util.StaticQuality.TYPE_TRAVEL_COLLECTION;

public class TravelDetailModel implements IModelDetail<TravelDetail> {
    @Override
    public void getData(int id , final CallBack<TravelDetail> callBack) {
        RetrofitManager.getInstance().createRs(TravelDetailApiService.class)
                .getTravelDetail(id)
                .enqueue(new Callback<DetailBean<TravelDetail>>() {
                    @Override
                    public void onResponse(@NotNull Call<DetailBean<TravelDetail>> call,
                                           @NotNull Response<DetailBean<TravelDetail>> response) {
                        if (response.body() != null){
                            callBack.onSucceed(response.body().getMessage());
                        }else{
                            callBack.onError("好像出了一点小问题");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailBean<TravelDetail>> call, @NotNull Throwable t) {
                        callBack.onError("好像出了一点小问题");
                    }
                });
    }

    @Override
    public void isStar(int typeId , final CallBackStar callBackStar) {

        RetrofitManager.getInstance().createRs(IsStarApiService.class)
                .isStar(typeId , TYPE_TRAVEL)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            boolean isStar;
                            isStar = response.body().getResult().equals("success");
                            callBackStar.onSucceed(isStar);
                        }else{
                            callBackStar.onError("出现异常（body==null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                        callBackStar.onError("出现异常（Failure）");
                    }
                });
    }

    @Override
    public void isCollection(int typeId,final CallBackStar callBackStar) {
        RetrofitManager.getInstance().createRs(IsCollectionApiService.class)
                .isCollection(typeId , TYPE_TRAVEL_COLLECTION)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            boolean isCollection;
                            isCollection = response.body().getResult().equals("success");
                            callBackStar.onSucceed(isCollection);
                        }else{
                            callBackStar.onError("出现异常（body==null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                        callBackStar.onError("出现异常（Failure）");
                    }
                });
    }

    @Override
    public void setStar(int articleId,final CallBackStar callBackStar) {
        RetrofitManager.getInstance().createRs(SetStarApiService.class)
                .setStar(articleId , TYPE_TRAVEL)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            if (response.body().getResult().equals("success")) callBackStar.onSucceed(true);
                            else callBackStar.onError("你已点过赞啦");
                        }else{
                            callBackStar.onError("出现异常（body==null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                        callBackStar.onError("出现异常（Failure）");
                    }
                });
    }

    @Override
    public void setCollection(int articleId,final CallBackStar callBackStar) {
        RetrofitManager.getInstance().createRs(SetCollectionApiService.class)
                .setCollection(articleId ,TYPE_TRAVEL_COLLECTION)
                .enqueue(new Callback<BackBean>() {
                    @Override
                    public void onResponse(@NotNull Call<BackBean> call, @NotNull Response<BackBean> response) {
                        if (response.body()!=null){
                            if (response.body().getResult().equals("success")) callBackStar.onSucceed(true);
                            else callBackStar.onError("你已收藏过啦");
                        }else{
                            callBackStar.onError("出现异常（body==null）");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<BackBean> call, @NotNull Throwable t) {
                        callBackStar.onError("出现异常（Failure）");
                    }
                });
    }
}
