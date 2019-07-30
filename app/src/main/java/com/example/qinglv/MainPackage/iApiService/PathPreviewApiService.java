package com.example.qinglv.MainPackage.iApiService;


import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.PATH_PREVIEW_URL;

/**
 * 路线的retrofit网络访问接口
 */
public interface PathPreviewApiService {
    //这里需要两个参数，一个是从哪项开始找，另一个是需要多少项
    @GET(PATH_PREVIEW_URL)
    Call<PreviewBean<Path>> getPath(@Query("num")int firstNum, @Query("size")int size);
}
