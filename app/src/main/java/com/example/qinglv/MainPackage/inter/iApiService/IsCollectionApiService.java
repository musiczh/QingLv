package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.bean.BackBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.COLLECTION_URL;

public interface IsCollectionApiService {

    @GET(COLLECTION_URL)
    Call<BackBean> isCollection(@Query("typeId")int typeId, @Query("type")int type);
}
