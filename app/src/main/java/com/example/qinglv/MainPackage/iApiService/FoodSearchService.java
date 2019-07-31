package com.example.qinglv.MainPackage.iApiService;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import static com.example.qinglv.util.StaticQuality.FOOD_SEARCH_URL;

/**
 * 美食搜索的网络接口
 */
public interface FoodSearchService {

   //这里需要三个参数，一个是关键词，一个是从哪项开始找，一个是需要多少项
    @GET(FOOD_SEARCH_URL)
    Call<PreviewBean<Food>> getSearchFood(@Query("key")String key ,@Query("num")int firstNum, @Query("size")int size);
}
