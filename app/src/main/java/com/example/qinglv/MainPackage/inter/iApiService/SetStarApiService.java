package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.bean.BackBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.qinglv.util.StaticQuality.STAR_URL;

public interface SetStarApiService {

    @Multipart
    @POST(STAR_URL)
    Call<BackBean> setStar(@Part("typeId")int typeId , @Part("type")int type);
}
