package com.example.qinglv.UserPackage.IApiSerice;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.qinglv.util.StaticQuality.REGISTER_LOGIN_URL;

public interface RegisterApiSerice {

//    @Multipart
    @FormUrlEncoded
    @POST(REGISTER_LOGIN_URL)
//    Call<JsonObject> getRsgister(@PartMap Map<String, RequestBody> requestBodyMap);
    Call<JsonObject> getRsgister(@Field("userName") String userName,
                               @Field("password") String password);
//    Call<register> getRsgister(@Part("userName") RequestBody username,
//                               @Part("password") RequestBody password);
}
