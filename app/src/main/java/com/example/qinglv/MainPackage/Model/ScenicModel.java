package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicPreviewApiService;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicSearchApiService;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScenicModel implements IModelPager<Scenic> {

    @Override
    public void getSearchData(String key, int firstNum, final int size, final CallBack<Scenic> callBack) {
        RetrofitManager.getInstance().createRs(ScenicSearchApiService.class)
                .getScenic(key,firstNum,size)
                .enqueue(new Callback<PreviewBean<Scenic>>() {
                    @Override
                    public void onResponse(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Response<PreviewBean<Scenic>> response) {
                        if (response.body()!=null) {
                            boolean isMore = true;
                            List<Scenic> scenicList = response.body().getMessage();
                            if (scenicList.size() < size || response.body().getResult().equals("noMore")) {
                                isMore = false;
                            }
                            callBack.onSucceed(scenicList, isMore);
                        }else {
                            callBack.onError("好像出了点小问题");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Throwable t) {
                        callBack.onError("没有查询到相关信息");
                    }
                });
    }

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, final int size, final IModelPager.CallBack<Scenic> callBack) {

        RetrofitManager.getInstance().createRs(ScenicPreviewApiService.class)
        .getScenic(firstNum,size)
        .enqueue(new Callback<PreviewBean<Scenic>>() {
            @Override
            public void onResponse(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Response<PreviewBean<Scenic>> response) {
                if (response.body()!=null) {
                    boolean isMore = true;
                    List<Scenic> scenicList = response.body().getMessage();
                    if (scenicList.size() < size || response.body().getResult().equals("noMore")) {
                        isMore = false;
                    }
                    callBack.onSucceed(scenicList, isMore);
                }else {
                    callBack.onError("好像出了点小问题");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Throwable t) {
                callBack.onError("好像出了点小问题");
            }
        });
        /*Observable<PreviewBean<Scenic>> observable =
                scenicPreviewApiService.getScenic(firstNum,size);

        observable
                .subscribe(new Subscriber<PreviewBean<Scenic>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError("访问服务器错误");
                    }

                    @Override
                    public void onNext(PreviewBean<Scenic> foodPreviewBean) {
                        boolean isMore = foodPreviewBean.getResult().equals("success");
                        List<Scenic> scenicList = foodPreviewBean.getMessage();
                        callBack.onSucceed(scenicList,isMore);
                    }
                });*/
    }
}
