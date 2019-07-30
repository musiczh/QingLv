package com.example.qinglv.MainPackage.iApiService;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.FOOD_PREVIEW_URL;

/**
 * 美食预览网络访问retrofit的辅助接口
 */
public interface FoodPreviewApiService {

    //这里需要两个参数，一个是从哪项开始找，另一个是需要多少项
    @GET(FOOD_PREVIEW_URL)
    Call<PreviewBean<Food>> getFood(@Query("num")int firstNum, @Query("size")int size);
}
