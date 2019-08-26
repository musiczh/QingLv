package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.bean.PostCommentBackBean;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static com.example.qinglv.util.StaticQuality.PATH_COMMENT_URL;
import static com.example.qinglv.util.StaticQuality.PATH_SEARCH_URL;

/**
 * 发送评论网络请求接口
 */
public interface PathPostCommentApiService {

    //这里需要两个个参数，一个是文章id，一个是评论内容
    @POST(PATH_COMMENT_URL)
    @Multipart
    Call<PostCommentBackBean> postPath(@Part("articleId")int articleId , @Part("content")String content);
}
