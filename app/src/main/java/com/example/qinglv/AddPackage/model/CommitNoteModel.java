package com.example.qinglv.AddPackage.model;

import android.util.Log;

import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.iApiService.CommitNoteApiService;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.util.RetrofitManager;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

/**
 * 提交游记的model层
 */

public class CommitNoteModel implements ICommitNoteContract.IModel {


    private ICommitNoteContract.IPresenter mPresenter;

    public CommitNoteModel(ICommitNoteContract.IPresenter presenter) {
        mPresenter = presenter;
    }



    @Override
    public void commitNote(final Map<String, RequestBody> params, List<MultipartBody.Part> files) {
        RetrofitManager.getInstance().createRs(CommitNoteApiService.class)
                .commitNote(params,files)
                .enqueue(new Callback<PreviewBean<NoteType>>() {

                    @Override
                    public void onResponse(Call<PreviewBean<NoteType>> call, Response<PreviewBean<NoteType>> response) {
                        Log.d("CommitNote","上传成功"+response.message()+response);
                        Log.d("id","------"+params.get("id"));
                        Log.d("tabId","------");
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<NoteType>> call, Throwable t) {

                    }

                });
    }
    }


