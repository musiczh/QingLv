package com.example.qinglv.UserPackage.Model;

import android.util.Log;

import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.IApiSerice.KeyApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.util.RetrofitManager;
import com.example.qinglv.util.RetrofitManagerAn;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements ILoginContract.Model {

    private ILoginContract.Presenter mPresenter;
    private Login login;
    String key;

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
                            assert response.body() != null;
//                            String responseData = response.body().string();
//                            JSONArray jsonArray = new JSONArray("[" + responseData + "]");
//                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            Log.v("123",response.body().string());
                            if(jsonObject.getString("result").equals("success")){
                                Log.v("123567","00000");
                                Gson gson = new Gson();
                                login = gson.fromJson(jsonObject.getString("message"),Login.class);
                                mPresenter.loginSuccess();
                            }
                            if(jsonObject.getString("result").equals("failed")){
                                Log.v("123567","111111");
                                String s = jsonObject.getString("message");
                                mPresenter.loginError(s);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mPresenter.loginError("访问服务器错误");
                    }
                });
    }

    public String getKey(){

        RetrofitManager.getInstance().createRs(KeyApiSerice.class)
                .getKey()
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        assert response.body() != null;
                        try {
                            JSONObject jsonObject =new JSONObject(response.body().string());
                            key = jsonObject.getString("message");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
        return key;
    }


}
