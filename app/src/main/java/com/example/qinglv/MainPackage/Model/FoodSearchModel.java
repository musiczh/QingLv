package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelSearch;
import com.example.qinglv.MainPackage.inter.iApiService.FoodSearchApiService;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodSearchModel implements IModelSearch<Food> {
    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(String key, int firstNum, int size, final IModelPager.CallBack<Food> callBack) {
        RetrofitManager.getInstance().createRs(FoodSearchApiService.class)
                .getFood(key, firstNum, size)
                .enqueue(new Callback<PreviewBean<Food>>() {
                    @Override
                    public void onResponse(@NonNull Call<PreviewBean<Food>> call, @NonNull Response<PreviewBean<Food>> response) {
                        assert response.body() != null;
                        boolean isMore = response.body().getResult().equals("success");
                        List<Food> foodList = response.body().getMessage();
                        callBack.onSucceed(foodList, isMore);
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<Food>> call, Throwable t) {
                        callBack.onError("访问服务器错误");
                    }
                });
    }
}