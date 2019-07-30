package com.example.qinglv.MainPackage.iApiService;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import static com.example.qinglv.MainPackage.util.StaticQuality.SCENIC_PREVIEW_URL;


/**
 * 风景的retrofit网络访问接口
 */
public interface ScenicPreviewApiService {
    //这里需要两个参数，一个是从哪项开始找，另一个是需要多少项
    @GET(SCENIC_PREVIEW_URL)
    Call<PreviewBean<Scenic>> getScenic(@Query("num")int firstNum, @Query("size")int size);
}
