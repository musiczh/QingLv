package com.example.qinglv.UserPackage.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.R;
import com.example.qinglv.UserPackage.Entity.register;
import com.example.qinglv.UserPackage.IApiSerice.KeyApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.RegisterApiSerice;
import com.example.qinglv.util.RSAEncrypt;
import com.example.qinglv.util.RetrofitManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
//                String username =  etUsername.toString().trim();
                String password = null;
                try {
                    password = RSAEncrypt.publicEncrypt(etPassword.getText().toString().trim(),"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh2wXYiWpCnJCOzvzgjP6p4Y+C75jD92+SmIRPcYwWKyfqXtw1XBaGE/EVHb4ypS+Ugfh6+AAKOi9udBQClI5QIkoIGA8r3i6Ald9lKakeXrcBkm1M3Kgb4V8n1QENNfAyPizewJTF7VWxTtaPHWJzTf+f/njXV2mOiZY4nLcdEQs/GgXm9H++bFcS+OEj/MWjxdiB1jG5Fv1acSf9+hkpP2tZT7rloQW/BanmGUX0Awd4r6G5tcjDnMs9bx0s9jDslOngqxqMHj8GQ2JUBrd03OP+tk8IXlHl3qYPpi32CwTztGZvS9L797cHBBesg9rhrjkdGBae9sVZIG9OLRMZQIDAQAB");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestBody username = RequestBody.create(MediaType.parse("multipart/form-data"),
                        etUsername.toString().trim());
                assert password != null;
                RequestBody pass = RequestBody.create(MediaType.parse("multipart/form-data"),
                        password);
                RetrofitManager.getInstance().createRs(RegisterApiSerice.class)
                        .getRsgister(username,pass)
                        .enqueue(new Callback<register>() {

                            @Override
                            public void onResponse(Call<register> call, Response<register> response) {
                                Log.v("444","565656486486486486");
                            }

                            @Override
                            public void onFailure(Call<register> call, Throwable t) {
                                Log.v("666","565656486486486486");
                            }
                        });
//                RetrofitManagerAn.getInstance().createRs(RegisterApiSerice.class)
//                        .getRsgister(username,pass)
//                        .enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                try {
//                                    String responseData = response.body().string();
//                                    System.out.println(responseData);
//                                    Log.i("tag",responseData);
//                                    JSONArray jsonArray = new JSONArray("[" + responseData + "]");
//                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
//                                    if(jsonObject.getString("result").equals("success")){
//                                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                                        Gson gson = new Gson();
//                                        //login = gson.fromJson(responseData,Login.class);
//                                    }
//                                    if(jsonObject.getString("result").equals("failed")){
//                                        String s = jsonObject.getString("message");
//                                        Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Toast.makeText(RegisterActivity.this,"访问服务器错误",Toast.LENGTH_SHORT).show();
//                            }
//                        });
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
                        Log.v("www",key);
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<String>> call, Throwable t) {
                    }
                });
        return key;
    }
}
