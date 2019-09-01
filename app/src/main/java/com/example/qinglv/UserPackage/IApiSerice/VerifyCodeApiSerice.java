package com.example.qinglv.UserPackage.IApiSerice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VerifyCodeApiSerice {
    @GET("user/verifyCode/")
    Call<ResponseBody> getVerifyCode();
}
