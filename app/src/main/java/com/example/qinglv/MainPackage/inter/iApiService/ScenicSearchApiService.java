package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.SCENIC_SEARCH_URL;

public interface ScenicSearchApiService {

    //这里需要三个参数，一个是关键词，一个是从哪项开始找，另一个是需要多少项
    @GET(SCENIC_SEARCH_URL)
    Call<PreviewBean<Scenic>> getScenic(@Query("key")String key, @Query("num")int firstNum, @Query("size")int size);
}
