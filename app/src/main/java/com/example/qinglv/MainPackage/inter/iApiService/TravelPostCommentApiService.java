package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.bean.PostCommentBackBean;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.qinglv.util.StaticQuality.SCENIC_COMMENT_URL;
import static com.example.qinglv.util.StaticQuality.TRAVEL_COMMENT_URL;

public interface TravelPostCommentApiService {
    //这里需要两个个参数，一个是文章id，一个是评论内容
    @POST(TRAVEL_COMMENT_URL)
    @Multipart
    Call<PostCommentBackBean> postTravel(@Part("articleId")int articleId , @Part("content")String content);
}
