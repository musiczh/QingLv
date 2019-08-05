package com.example.qinglv.UserPackage.IApiSerice;

import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.Entity.register;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.REGISTER_LOGIN_URL;

public interface RegisterApiSerice {

    @GET(REGISTER_LOGIN_URL)
    Call<ResponseBody> getRsgister(@Query("userName")String username,
                                @Query("password")String password);
}
