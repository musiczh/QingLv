package com.example.qinglv.AddPackage.iApiService;

import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.qinglv.util.StaticQuality.NOTE_TYPE_URL;


/**
 * 游记种类的网络接口
 */

public interface NoteTypeApiService {

    @GET(NOTE_TYPE_URL)
    Call<PreviewBean<NoteType>> getNoteType();

}
