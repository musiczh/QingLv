package com.example.qinglv.MainPackage.inter.iApiService;

import com.example.qinglv.MainPackage.bean.CommentPostBean;
import com.example.qinglv.MainPackage.bean.BackBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.qinglv.util.StaticQuality.PATH_COMMENT_URL;

/**
 * 发送评论网络请求接口
 */
public interface PathPostCommentApiService {

    //这里需要两个个参数，一个是文章id，一个是评论内容
    @POST(PATH_COMMENT_URL)
    Call<BackBean> postPath(@Body CommentPostBean commentPostBean);
}
