package com.example.qinglv.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

public class RetrofitManagerAn {
    //静态内部属性
    private static RetrofitManagerAn retrofitManagerAn;
    private Retrofit mRetrofit;

    //构造器私有，这个工具类只有一个实例
    private RetrofitManagerAn(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    //获取单例的静态方法。synchronized是上锁的意思，就是多个线程只能同时被使用一次
    public static synchronized RetrofitManagerAn getInstance(){
        if (retrofitManagerAn == null){
            retrofitManagerAn = new RetrofitManagerAn();
        }
        return retrofitManagerAn;
    }

    //利用泛型传入接口class返回接口实例
    public <T> T createRs(Class<T> ser){
        return mRetrofit.create(ser);
    }
}
