package com.example.qinglv.AddPackage.iApiService;

import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

import static com.example.qinglv.util.StaticQuality.NOTE_COMMIT_URL;

/**
 * 提交有照片的游记的网络接口
 */

public interface CommitPhotoNoteApiService {


    @Multipart
    @POST(NOTE_COMMIT_URL)
    Call<PreviewBean<NoteType>> commitPhotoNote(@PartMap Map<String,RequestBody> data, @Part List<MultipartBody.Part> photos);
}


