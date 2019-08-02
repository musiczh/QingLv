package com.example.qinglv.AddPackage.iApiService;

import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;

import retrofit2.Call;
import retrofit2.http.GET;




/**
 * 游记种类的网络接口
 */

public interface NoteTypeApiService {

    @GET()
    Call<PreviewBean<NoteType>> getNoteType();

}
