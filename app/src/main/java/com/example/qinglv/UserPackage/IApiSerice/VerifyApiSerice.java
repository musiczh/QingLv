package com.example.qinglv.UserPackage.IApiSerice;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VerifyApiSerice {

    @GET("user/forgetCode/")
    Call<JSONObject> getVerifyCode();
}
