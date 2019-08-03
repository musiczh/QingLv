package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.FoodPreviewApiService;
import com.example.qinglv.MainPackage.iApiService.FoodSearchApiService;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

/**
 * 美食预览界面model层
 */
public class FoodModel implements IModelPager<Food> {

    @Override
    public void getSearchData(String key, int firstNum, int size,final CallBack<Food> callBack) {
        RetrofitManager.getInstance().createRs(FoodSearchApiService.class)
                .getFood(key,firstNum,size)
                .enqueue(new Callback<PreviewBean<Food>>() {
                    @Override
                    public void onResponse(@NonNull Call<PreviewBean<Food>> call, @NonNull Response<PreviewBean<Food>> response) {
                        assert response.body() != null;
                        boolean isMore = response.body().getResult().equals("success");
                        List<Food> foodList = response.body().getMessage();
                        callBack.onSucceed(foodList,isMore);
                    }

                    @Override
                    public void onFailure(@NonNull Call<PreviewBean<Food>> call, @NonNull Throwable t) {
                        callBack.onError("访问服务器错误");
                    }
                });
    }

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final CallBack<Food> callBack) {
        RetrofitManager.getInstance().createRs(FoodPreviewApiService.class)
            .getFood(firstNum,size)
            .enqueue(new Callback<PreviewBean<Food>>() {
                @Override
                public void onResponse(@NonNull Call<PreviewBean<Food>> call, @NonNull Response<PreviewBean<Food>> response) {
                    assert response.body() != null;
                    boolean isMore = response.body().getResult().equals("success");
                    List<Food> foodList = response.body().getMessage();
                    callBack.onSucceed(foodList,isMore);
                }

                @Override
                public void onFailure(@NonNull Call<PreviewBean<Food>> call, @NonNull Throwable t) {
                    callBack.onError("访问服务器错误");
                }
            });

    }
        /*Observable<PreviewBean<Food>> observable =
                foodPreviewApiService.getFood(firstNum,size);

        observable.subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PreviewBean<Food>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError("访问服务器错误");
                    }

                    @Override
                    public void onNext(PreviewBean<Food> foodPreviewBean) {
                        boolean isMore = foodPreviewBean.getResult().equals("success");
                        List<Food> foodList = foodPreviewBean.getMessage();
                        callBack.onSucceed(foodList,isMore);
                    }
                });*/



}
