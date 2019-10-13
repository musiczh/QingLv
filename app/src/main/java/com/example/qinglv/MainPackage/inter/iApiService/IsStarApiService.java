package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.bean.BackBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.STAR_URL;

public interface IsStarApiService {

    @GET(STAR_URL)
    Call<BackBean> isStar(@Query("typeId")int typeId,@Query("type")int type);
}
