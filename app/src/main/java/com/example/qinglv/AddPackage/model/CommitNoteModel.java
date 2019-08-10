package com.example.qinglv.AddPackage.model;

import android.util.Log;

import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.iApiService.CommitNoteApiService;
import com.example.qinglv.AddPackage.iApiService.CommitPhotoNoteApiService;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 提交游记的model层
 */

public class CommitNoteModel implements ICommitNoteContract.IModel {


    private ICommitNoteContract.IPresenter mPresenter;

    public CommitNoteModel(ICommitNoteContract.IPresenter presenter) {
        mPresenter = presenter;
    }


    //有照片时调用
    @Override
    public void commitPhotoNote(final Map<String, RequestBody> params, List<MultipartBody.Part> files) {
        RetrofitManager.getInstance().createRs(CommitPhotoNoteApiService.class)
                .commitPhotoNote(params, files)
                .enqueue(new Callback<PreviewBean<NoteType>>() {

                    @Override
                    public void onResponse(Call<PreviewBean<NoteType>> call, Response<PreviewBean<NoteType>> response) {
                        Log.d("CommitNote", "上传成功" + response.message() + response);
                        Log.d("id", "------" + params.get("id"));
                        Log.d("tabId", "------");
                        mPresenter.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<NoteType>> call, Throwable t) {
                        Log.d("failure", "------" + t.toString());
                        mPresenter.setErrorToast("访问服务器失败，请稍后重试");
                    }
                });
    }

    //无照片时调用
    @Override
    public void commitNote(RequestBody body) {
        RetrofitManager.getInstance().createRs(CommitNoteApiService.class)
                .commitNote(body)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("CommitNote", "上传成功" + response.message() + response);
                        mPresenter.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mPresenter.setErrorToast("访问服务器失败，请稍后重试");
                    }


                });
    }
}


