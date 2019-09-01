package com.example.qinglv.UserPackage.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinglv.R;
import com.example.qinglv.UserPackage.IApiSerice.VerifyCodeApiSerice;
import com.example.qinglv.util.AddCookiesInterceptor;
import com.example.qinglv.util.ReceivedCookiesInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

public class ForgetActivity extends AppCompatActivity {

    private EditText edUsername,edPassword,edVerify;
    private TextView tvFinish,tvVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        edUsername = findViewById(R.id.ed_ft_username);
        edPassword = findViewById(R.id.ed_ft_password);
        edVerify = findViewById(R.id.ed_ft_verify);
        tvFinish = findViewById(R.id.tv_ft_finish);
        tvVerify = findViewById(R.id.tv_ft_verify);
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void loginSuccess() {
        Toast.makeText(ForgetActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    public void loginError(String msg) {
        Toast.makeText(ForgetActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new ReceivedCookiesInterceptor(ForgetActivity.this))//将请求图片验证码的cookie提取出来
            .addInterceptor(new AddCookiesInterceptor(ForgetActivity.this)) //将提取的cookie放入
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();

    public void getVerify() {

        VerifyCodeApiSerice service = retrofit.create(VerifyCodeApiSerice.class);
        Call<ResponseBody> call = service.getVerifyCode();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                assert response.body() != null;
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginError("访问服务器错误");
            }
        });
    }

}
