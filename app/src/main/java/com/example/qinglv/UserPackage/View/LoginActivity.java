package com.example.qinglv.UserPackage.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qinglv.R;
import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Presenter.LoginPresenter;
import com.example.qinglv.util.RSAEncrypt;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    private EditText etUsername,etPassword,etVerify;
    private TextView tvLogin,tvRegister,tvForget;
    private ImageView ivVerify;
    private LoginPresenter mLoginPresenter = new LoginPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        tvLogin = findViewById(R.id.tv_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForget = findViewById(R.id.tv_forget);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etVerify = findViewById(R.id.et_verify);
        ivVerify = findViewById(R.id.iv_verify);
        String url = BASE_URL + "user/verifyCode";
        Glide.with(LoginActivity.this)
                .load(url)
                .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                .skipMemoryCache(true)//跳过内存缓存
                .into(ivVerify);
        ivVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = BASE_URL + "user/verifyCode";
                Glide.with(LoginActivity.this)
                        .load(url)
                        .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                        .skipMemoryCache(true)//跳过内存缓存
                        .into(ivVerify);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = null;
                try {
                    password = RSAEncrypt.publicEncrypt(etPassword.getText().toString().trim(),mLoginPresenter.getKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String verify = etVerify.getText().toString().trim();
                mLoginPresenter.login(username,password,verify);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

}
