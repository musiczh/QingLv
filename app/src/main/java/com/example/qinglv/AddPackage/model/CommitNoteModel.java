package com.example.qinglv.AddPackage.model;

import android.util.Log;

import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.iApiService.CommitNoteApiService;
import com.example.qinglv.util.RetrofitManager;

import java.lang.reflect.Array;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    public void commitNote(int id, String title, List<MultipartBody.Part> photos, String content, final int tabId) {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        //2.获取MyServer接口服务对象
        CommitNoteApiService apiService = retrofit.create(CommitNoteApiService.class);

        //3.获取Call对象
        //方式一
        Call<ResponseBody> call = apiService.commitNote(id,title, photos,content, tabId);

        //4.Call对象执行请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mPresenter.onSuccess();
                Log.d("CommitNoteModel","网络请求成功"+tabId);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}

