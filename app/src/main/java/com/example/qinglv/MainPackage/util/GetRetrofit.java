package com.example.qinglv.MainPackage.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.qinglv.MainPackage.util.StaticQuality.BASE_URL;

/**
 * 获得retrofit的单一实例，不用每次都去写一次获得retrofit
 */
public class GetRetrofit {

    private Retrofit retrofit;

    private GetRetrofit(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static class SingleHolder{
        private static final GetRetrofit INSTANCE = new GetRetrofit();
    }

    public static GetRetrofit getInstance(){
        return SingleHolder.INSTANCE;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
