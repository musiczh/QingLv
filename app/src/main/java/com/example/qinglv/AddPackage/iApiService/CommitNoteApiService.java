package com.example.qinglv.AddPackage.iApiService;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.qinglv.util.StaticQuality.NOTE_COMMIT_URL;

/**
 * 提交没照片的游记的网络接口
 */

public interface CommitNoteApiService {

    @POST(NOTE_COMMIT_URL)
    Call<ResponseBody> commitNote(@Body RequestBody body);
}
