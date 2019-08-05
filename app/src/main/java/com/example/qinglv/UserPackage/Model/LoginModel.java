package com.example.qinglv.UserPackage.Model;

import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.IApiSerice.KeyApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.util.RetrofitManager;
import com.example.qinglv.util.RetrofitManagerAn;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
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
                        mPresenter.loginError("访问服务器错误");
                    }
                });
    }

    public String getKey(){

        RetrofitManager.getInstance().createRs(KeyApiSerice.class)
                .getKey()
                .enqueue(new Callback<PreviewBean<String>>() {

                    @Override
                    public void onResponse(Call<PreviewBean<String>> call, Response<PreviewBean<String>> response) {
                        assert response.body() != null;
                       key = response.body().getMessage().get(0);
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<String>> call, Throwable t) {

                    }
                });
        return key;
    }


}
