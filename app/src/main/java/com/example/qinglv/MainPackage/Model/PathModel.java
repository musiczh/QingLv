package com.example.qinglv.MainPackage.Model;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.PathPreviewApiService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.example.qinglv.MainPackage.util.StaticQuality.BASE_URL;

/**
 * 美食预览界面model层
 */
public class PathModel implements IModelPager<Path> {

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final CallBack<Path> callBack) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        PathPreviewApiService pathPreviewApiService = retrofit.create(PathPreviewApiService.class);
        Observable<PreviewBean<Path>> observable =
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
                });
    }


}