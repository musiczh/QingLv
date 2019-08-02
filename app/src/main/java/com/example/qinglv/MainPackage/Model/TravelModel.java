package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.MainPackage.iApiService.TravelPreviewApiService;
import com.example.qinglv.util.RetrofitManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TravelModel implements IModelPager<Travel> {
    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getData(int firstNum, int size, final CallBack<Travel> callBack) {

        RetrofitManager.getInstance().createRs(TravelPreviewApiService.class)
        .getPath(firstNum, size)
        .enqueue(new Callback<PreviewBean<Travel>>() {
            @Override
            public void onResponse(@NonNull Call<PreviewBean<Travel>> call, @NonNull Response<PreviewBean<Travel>> response) {
                assert response.body() != null;
                boolean isMore = response.body().getResult().equals("success");
                List<Travel> travelList = response.body().getMessage();
                callBack.onSucceed(travelList,isMore);
            }

            @Override
            public void onFailure(@NonNull Call<PreviewBean<Travel>> call, @NonNull Throwable t) {

            }
        });
        /*Observable<PreviewBean<Travel>> observable =
                travelPreviewApiService.getPath(firstNum,size);

        observable.subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PreviewBean<Travel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError("访问服务器错误");
                    }

                    @Override
                    public void onNext(PreviewBean<Travel> foodPreviewBean) {
                        boolean isMore = foodPreviewBean.getResult().equals("success");
                        List<Travel> travelList = foodPreviewBean.getMessage();
                        callBack.onSucceed(travelList,isMore);
                    }
                });*/
    }
}
