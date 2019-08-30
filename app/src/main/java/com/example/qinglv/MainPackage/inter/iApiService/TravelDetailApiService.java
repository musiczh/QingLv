package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.TravelDetail;
import com.example.qinglv.MainPackage.bean.DetailBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static com.example.qinglv.util.StaticQuality.PATH_DETAIL_URL;
import static com.example.qinglv.util.StaticQuality.TRAVEL_DETAIL_URL;

public interface TravelDetailApiService {
    //这里需要一个参数，也是路线的id
    @GET(TRAVEL_DETAIL_URL)
    Call<DetailBean<TravelDetail>> getTravelDetail(@Path("id") int id);
}
