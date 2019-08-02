package com.example.qinglv.UserPackage.IApiSerice;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.REGISTER_LOGIN_URL;

public interface RegisterApiSerice {

    @GET(REGISTER_LOGIN_URL)
    Call<PreviewBean<Food>> getFood(@Query("userName")String username,
                                    @Query("password")String password);
}
