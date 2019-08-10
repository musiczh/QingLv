package com.example.qinglv.UserPackage.IApiSerice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.qinglv.util.StaticQuality.USER_LOGIN_URL;

public interface LoginApiSerice {
    //三个参数，用户名，密码，验证码
    @POST(USER_LOGIN_URL)
    @FormUrlEncoded
    Call<ResponseBody> getLogin(@Field("userName")String username,
                                @Field("password")String password,
                                @Field("verifyCode")String verifyCode);
}
