package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.FOOD_PREVIEW_URL;
import static com.example.qinglv.util.StaticQuality.SCENIC_COMMENT_URL;

/**
 * 风景评论的网络请求接口
 */
public interface ScenicCommentApiService {

    //这里需要三个参数，一个是文章id，一个是从哪项开始找，另一个是需要多少项
    @GET(SCENIC_COMMENT_URL)
    Call<PreviewBean<Comment>> getComment(@Query("articleId")int articleId,
                                          @Query("num")int firstNum, @Query("size")int size);
}
