package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.ScenicPreviewApiService;
import com.example.qinglv.util.RetrofitManager;

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

public class ScenicModel implements IModelPager<Scenic> {
    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final IModelPager.CallBack<Scenic> callBack) {

        RetrofitManager.getInstance().createRs(ScenicPreviewApiService.class)
        .getScenic(firstNum,size)
        .enqueue(new Callback<PreviewBean<Scenic>>() {
            @Override
            public void onResponse(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Response<PreviewBean<Scenic>> response) {
                assert response.body() != null;
                boolean isMore = response.body().getResult().equals("success");
                List<Scenic> scenicList = response.body().getMessage();
                callBack.onSucceed(scenicList,isMore);
            }

            @Override
            public void onFailure(@NonNull Call<PreviewBean<Scenic>> call, @NonNull Throwable t) {
                callBack.onError("访问服务器错误");
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
