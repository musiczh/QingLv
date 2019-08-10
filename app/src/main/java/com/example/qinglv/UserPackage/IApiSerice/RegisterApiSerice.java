package com.example.qinglv.UserPackage.IApiSerice;

import com.example.qinglv.UserPackage.Entity.register;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.qinglv.util.StaticQuality.REGISTER_LOGIN_URL;

public interface RegisterApiSerice {

    @POST(REGISTER_LOGIN_URL)
    @Multipart
    Call<register> getRsgister(@Part("userName") RequestBody username,
                               @Part("password") RequestBody password);
}
