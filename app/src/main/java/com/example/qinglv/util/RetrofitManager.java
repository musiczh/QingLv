package com.example.qinglv.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

/**
 * Retrofit单例工具类
 * 内部自己创建一个retrofit实例，调用里面的方法传入一个接口class就可以返回一个接口实例
 * 调用示例：
 * FoodPreviewApiService foodPreviewApiService = RetrofitManager.getInstance().createRs(FoodPreviewApiService.class);
 */

public class RetrofitManager {
    //静态内部属性
    private static RetrofitManager retrofitManager;
    private Retrofit mRetrofit;

    //构造器私有，这个工具类只有一个实例
    private RetrofitManager(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    //获取单例的静态方法。synchronized是上锁的意思，就是多个线程只能同时被使用一次
    public static synchronized RetrofitManager getInstance(){
        if (retrofitManager == null){
            retrofitManager = new RetrofitManager();
        }
        return
                retrofitManager;
    }

    //利用泛型传入接口class返回接口实例
    public <T> T createRs(Class<T> ser){
        return mRetrofit.create(ser);
    }
}
