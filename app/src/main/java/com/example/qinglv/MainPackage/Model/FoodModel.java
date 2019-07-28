package com.example.qinglv.MainPackage.Model;

import android.widget.Adapter;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.iModel.IModelFood;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.FoodPreviewApiService;
import com.example.qinglv.MainPackage.util.GetRetrofit;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 美食预览界面model层
 */
public class FoodModel implements IModelFood {

    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final CallBack callBack) {
        Retrofit retrofit = GetRetrofit.getInstance().getRetrofit();
        FoodPreviewApiService foodPreviewApiService = retrofit.create(FoodPreviewApiService.class);
        Observable<PreviewBean<Food>> observable =
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
                });
    }


}
