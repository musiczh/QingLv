package com.example.qinglv.UserPackage.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.util.RetrofitManager;
import com.example.qinglv.util.RetrofitManagerAn;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements ILoginContract.Model {

    private ILoginContract.Presenter mPresenter;
    private Login login;

    public LoginModel(ILoginContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void login(String username, String password,String verify) {
        RetrofitManagerAn.getInstance().createRs(LoginApiSerice.class)
                .getLogin(username,password,verify)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String responseData = response.body().string();
                            JSONArray jsonArray = new JSONArray("[" + responseData + "]");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            if(jsonObject.getString("result").equals("success")){
                                Gson gson = new Gson();
                                login = gson.fromJson(responseData,Login.class);
                                mPresenter.loginSuccess();
                            }
                            if(jsonObject.getString("result").equals("failed")){
                                String s = jsonObject.getString("message");
                                mPresenter.loginError(s);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    public void verify() {

    }


}
