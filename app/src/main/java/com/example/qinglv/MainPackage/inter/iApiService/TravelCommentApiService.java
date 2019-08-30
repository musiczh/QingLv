package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.SCENIC_COMMENT_URL;
import static com.example.qinglv.util.StaticQuality.TRAVEL_COMMENT_URL;

/**
 * 游记评论的网络请求接口
 */
public interface TravelCommentApiService {

    //这里需要三个参数，一个是文章id，一个是从哪项开始找，另一个是需要多少项
    @GET(TRAVEL_COMMENT_URL)
    Call<ResponseBody> getComment(@Query("articleId")int articleId,
                                  @Query("num")int firstNum, @Query("size")int size);
}
