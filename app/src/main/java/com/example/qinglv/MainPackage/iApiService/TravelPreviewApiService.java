package com.example.qinglv.MainPackage.iApiService;


import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import static com.example.qinglv.MainPackage.util.StaticQuality.TRAVEL_PREVIEW_URL;

/**
 * 游记的retrofit网络访问接口
 */
public interface TravelPreviewApiService {
    //这里需要两个参数，一个是从哪项开始找，另一个是需要多少项
    @GET(TRAVEL_PREVIEW_URL)
    Observable<PreviewBean<Travel>> getPath(@Query("firstNum")int firstNum, @Query("size")int size);
}
