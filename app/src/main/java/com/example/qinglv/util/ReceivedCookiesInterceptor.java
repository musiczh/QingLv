package com.example.qinglv.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.qinglv.UserPackage.View.LoginActivity;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;

import static android.content.Context.MODE_PRIVATE;

public class ReceivedCookiesInterceptor implements Interceptor {

    private Context mcontext;

    private Activity mactivity;

    private  LoginActivity mLoginActivity;

//    public ReceivedCookiesInterceptor(LoginActivity loginActivity) {
//        mLoginActivity =loginActivity;
//    }




    public ReceivedCookiesInterceptor( Context context){
        mcontext  = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor config = mcontext.getSharedPreferences("config", MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.apply();
        }

        return originalResponse;
    }
}
