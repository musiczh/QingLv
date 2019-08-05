package com.example.qinglv.UserPackage.IApiSerice;

import com.example.qinglv.MainPackage.bean.PreviewBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


import static com.example.qinglv.util.StaticQuality.USER_LOGIN_URL;

public interface KeyApiSerice {
    @GET("user/getKey/")
    Call<PreviewBean<String>> getKey();
}
