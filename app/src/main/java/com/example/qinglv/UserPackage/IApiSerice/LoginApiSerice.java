package com.example.qinglv.UserPackage.IApiSerice;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.USER_LOGIN_URL;

public interface LoginApiSerice {
    //三个参数，用户名，密码，验证码
    @GET(USER_LOGIN_URL)
    Call<PreviewBean<Food>> getFood(@Query("userName")String username,
                                    @Query("password")String password,@Query("verifyCode")String verifyCode);
}
