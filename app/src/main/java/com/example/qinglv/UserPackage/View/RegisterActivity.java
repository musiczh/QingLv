package com.example.qinglv.UserPackage.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.R;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.Entity.register;
import com.example.qinglv.UserPackage.IApiSerice.KeyApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.RegisterApiSerice;
import com.example.qinglv.util.RSAEncrypt;
import com.example.qinglv.util.RetrofitManager;
import com.example.qinglv.util.RetrofitManagerAn;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvRegister;
    private EditText etUsername,etPassword;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        tvRegister = findViewById(R.id.tv_rt_register);
        etUsername = findViewById(R.id.et_rt_username);
        etPassword = findViewById(R.id.et_rt_password);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.toString().trim();
                String password = null;
                try {
                    password = RSAEncrypt.publicEncrypt(etPassword.getText().toString().trim(),getKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RetrofitManagerAn.getInstance().createRs(RegisterApiSerice.class)
                        .getRsgister(username,password)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String responseData = response.body().string();
                                    JSONArray jsonArray = new JSONArray("[" + responseData + "]");
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    if(jsonObject.getString("result").equals("success")){
                                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                        Gson gson = new Gson();
                                        //login = gson.fromJson(responseData,Login.class);
                                    }
                                    if(jsonObject.getString("result").equals("failed")){
                                        String s = jsonObject.getString("message");
                                        Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(RegisterActivity.this,"访问服务器错误",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private String getKey(){

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
