package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.bean.DetailBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.SCENIC_DETAIL_URL;

public interface ScenicDetailApiService {
    //这里需要一个参数，也是风景的id
    @GET(SCENIC_DETAIL_URL)
    Call<DetailBean<Scenic>> getScenic(@Query("id")int size);
}
