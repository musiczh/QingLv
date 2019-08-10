package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.bean.DetailBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.PATH_DETAIL_URL;

/**
 * 获取路线详情实例的网路接口
 */
public interface PathDetailApiService {

    //这里需要一个参数，也是路线的id
    @GET(PATH_DETAIL_URL)
    Call<DetailBean<Path>> getPath(@Query("id")int id);
}
