package com.example.qinglv.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;

import static android.content.Context.MODE_PRIVATE;

public class AddCookiesInterceptor implements Interceptor {

    private Context mcontext;

    private AppCompatActivity mAppCompatActivity;

    private Activity mActivity;
    private Context mContext;
//    public  AddCookiesInterceptor(Activity activity){
//        this.mActivity = activity;
//    }
    public  AddCookiesInterceptor(Context context){
        this.mContext = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) mContext.getSharedPreferences("config",
                MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
