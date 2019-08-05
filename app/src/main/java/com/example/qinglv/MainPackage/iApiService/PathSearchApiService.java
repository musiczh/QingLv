package com.example.qinglv.MainPackage.iApiService;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.FOOD_SEARCH_URL;
import static com.example.qinglv.util.StaticQuality.PATH_SEARCH_URL;

public interface PathSearchApiService {

    //这里需要三个参数，一个是关键词，一个是从哪项开始找，另一个是需要多少项
    @GET(PATH_SEARCH_URL)
    Call<PreviewBean<Path>> getPath(@Query("key")String key, @Query("num")int firstNum, @Query("size")int size);
}
