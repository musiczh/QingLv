package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.bean.BackBean;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.qinglv.util.StaticQuality.COLLECTION_URL;

public interface SetCollectionApiService {

    @Multipart
    @POST(COLLECTION_URL)
    Call<BackBean> setCollection(@Part("typeId")int typeId , @Part("type")int type);
}
