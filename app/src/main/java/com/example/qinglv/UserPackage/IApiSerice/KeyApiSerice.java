package com.example.qinglv.UserPackage.IApiSerice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface KeyApiSerice {
    @GET("user/getKey/")
    Call<ResponseBody> getKey();
}
