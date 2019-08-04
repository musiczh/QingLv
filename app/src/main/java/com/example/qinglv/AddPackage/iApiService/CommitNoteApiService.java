package com.example.qinglv.AddPackage.iApiService;

import java.lang.reflect.Array;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.qinglv.util.StaticQuality.NOTE_COMMIT_URL;

/**
 * 提交游记的网络接口
 */

public interface CommitNoteApiService {


    @Multipart
    @POST(NOTE_COMMIT_URL)
    Call<ResponseBody> commitNote(@Part("id") int id, @Part("title") String title, @Part()List<MultipartBody.Part> photo, @Part String content, @Part("tabIe")int tabIe);
}


