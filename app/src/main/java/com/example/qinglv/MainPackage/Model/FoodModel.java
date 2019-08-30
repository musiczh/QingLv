package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.inter.iApiService.FoodPreviewApiService;
import com.example.qinglv.MainPackage.inter.iApiService.FoodSearchApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelSearchApiService;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import com.example.qinglv.util.RetrofitManager;
import com.example.qinglv.util.RetrofitManagerAn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 美食预览界面model层
 */
public class FoodModel implements IModelPager<Food> {

    @Override
    public void getSearchData(String key, final int firstNum, final int size, final CallBack<Food> callBack) {
        RetrofitManagerAn.getInstance().createRs(FoodSearchApiService.class)
                .getFood(key,firstNum, size)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                boolean isMore = true;
                                String s = response.body().string();
                                JSONObject jsonObject = new JSONObject(s);
                                if (!jsonObject.getString("result").equals("failed")){
                                    JSONArray jsonArray = jsonObject.getJSONArray("message");
                                    Gson gson = new Gson();
                                    List<Food> list = gson.fromJson(jsonArray.toString(),
                                            new TypeToken<List<Food>>(){}.getType());
                                    if (list.size()<size) isMore = false;
                                    if (list.isEmpty()){
                                        callBack.onError("好像出了点小问题(json异常)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                                    }else {
                                        callBack.onSucceed(list, isMore);
                                    }
                                }else{
                                    if (firstNum == 0) callBack.onError(jsonObject.getString("message") , RecyclerViewAdapterWrapper.NO_ARTICLE);
                                    else callBack.onError(jsonObject.getString("message") , RecyclerViewAdapterWrapper.NO_MORE);
                                }
                            } catch (JSONException e) {
                                callBack.onError("好像出了点小问题(json异常)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            callBack.onError("好像出了点小问题(body=null)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        callBack.onError("好像出了点小问题(onFailure)" , RecyclerViewAdapterWrapper.NO_INTERNET);
                    }
                });
    }

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, final int size, final CallBack<Food> callBack) {
        RetrofitManager.getInstance().createRs(FoodPreviewApiService.class)
            .getFood(firstNum,size)
            .enqueue(new Callback<PreviewBean<Food>>() {
                @Override
                public void onResponse(@NonNull Call<PreviewBean<Food>> call, @NonNull Response<PreviewBean<Food>> response) {
                    if (response.body()!=null) {
                        boolean isMore = true;
                        List<Food> foodList = response.body().getMessage();
                        if (foodList.size() < size || response.body().getResult().equals("noMore")) {
                            isMore = false;
                        }
                        callBack.onSucceed(foodList, isMore);
                    }else {
                        callBack.onError("好像出了点小问题", RecyclerViewAdapterWrapper.NO_INTERNET);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PreviewBean<Food>> call, @NonNull Throwable t) {
                    callBack.onError("好像出了点小问题", RecyclerViewAdapterWrapper.NO_INTERNET);
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
