package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiService.PathPreviewApiService;
import com.example.qinglv.MainPackage.inter.iApiService.PathSearchApiService;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 美食预览界面model层
 */
public class PathModel implements IModelPager<Path> {


    @Override
    public void getSearchData(String key, int firstNum, final int size, final CallBack<Path> callBack) {
        RetrofitManager.getInstance().createRs(PathSearchApiService.class)
                .getPath(key,firstNum,size)
                .enqueue(new Callback<PreviewBean<Path>>() {
                    @Override
                    public void onResponse(@NonNull Call<PreviewBean<Path>> call, @NonNull Response<PreviewBean<Path>> response) {
                        if (response.body()!=null) {
                            boolean isMore = true;
                            List<Path> list = response.body().getMessage();
                            if (list.size() < size || response.body().getResult().equals("noMore")) {
                                isMore = false;
                            }
                            callBack.onSucceed(list, isMore);
                        }else {
                            callBack.onError("好像出了点小问题");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<PreviewBean<Path>> call, @NonNull Throwable t) {
                        callBack.onError("，没有查询到相关内容");
                    }
                });
    }

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, final int size, final CallBack<Path> callBack) {

        RetrofitManager.getInstance().createRs(PathPreviewApiService.class)
        .getPath(firstNum,size)
        .enqueue(new Callback<PreviewBean<Path>>() {
            @Override
            public void onResponse(@NonNull Call<PreviewBean<Path>> call, @NonNull Response<PreviewBean<Path>> response) {
                if (response.body()!=null) {
                    boolean isMore = true;
                    List<Path> list = response.body().getMessage();
                    if (list.size() < size || response.body().getResult().equals("noMore")) {
                        isMore = false;
                    }
                    callBack.onSucceed(list, isMore);
                }else {
                    callBack.onError("好像出了点小问题");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PreviewBean<Path>> call, @NonNull Throwable t) {
                callBack.onError("好像出了点小问题");
            }
        });
        /*Observable<PreviewBean<Path>> observable =
                pathPreviewApiService.getPath(firstNum,size);

        observable.subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PreviewBean<Path>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError("访问服务器错误");
                    }

                    @Override
                    public void onNext(PreviewBean<Path> foodPreviewBean) {
                        boolean isMore = foodPreviewBean.getResult().equals("success");
                        List<Path> list = foodPreviewBean.getMessage();
                        callBack.onSucceed(list,isMore);
                    }
                });*/
    }


}