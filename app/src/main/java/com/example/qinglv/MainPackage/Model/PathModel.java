package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.PathPreviewApiService;
import com.example.qinglv.MainPackage.iApiService.PathSearchApiService;
import com.example.qinglv.util.RetrofitManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

/**
 * 美食预览界面model层
 */
public class PathModel implements IModelPager<Path> {


    @Override
    public void getSearchData(String key, int firstNum, int size, final CallBack<Path> callBack) {
        RetrofitManager.getInstance().createRs(PathSearchApiService.class)
                .getPath(key,firstNum,size)
                .enqueue(new Callback<PreviewBean<Path>>() {
                    @Override
                    public void onResponse(@NonNull Call<PreviewBean<Path>> call, @NonNull Response<PreviewBean<Path>> response) {
                        List<Path> list;
                        boolean isMore;
                        if (response.body() != null) {
                            isMore = response.body().getResult().equals("success");
                            list = response.body().getMessage();
                        }else{
                            isMore = true;
                            list = new ArrayList<>();
                        }
                        callBack.onSucceed(list,isMore);
                    }

                    @Override
                    public void onFailure(@NonNull Call<PreviewBean<Path>> call, @NonNull Throwable t) {
                        callBack.onError("访问服务器错误");
                    }
                });
    }

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final CallBack<Path> callBack) {

        RetrofitManager.getInstance().createRs(PathPreviewApiService.class)
        .getPath(firstNum,size)
        .enqueue(new Callback<PreviewBean<Path>>() {
            @Override
            public void onResponse(@NonNull Call<PreviewBean<Path>> call, @NonNull Response<PreviewBean<Path>> response) {
                List<Path> list;
                boolean isMore;
                if (response.body() != null) {
                    isMore = response.body().getResult().equals("success");
                    list = response.body().getMessage();
                }else{
                    isMore = true;
                    list = new ArrayList<>();
                }
                callBack.onSucceed(list,isMore);
            }

            @Override
            public void onFailure(@NonNull Call<PreviewBean<Path>> call, @NonNull Throwable t) {
                callBack.onError("访问服务器错误");
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