package com.example.qinglv.UserPackage.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinglv.R;
import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.VerifyCodeApiSerice;
import com.example.qinglv.util.RSAEncrypt;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    private EditText etUsername,etPassword,etVerify;
    private TextView tvLogin,tvRegister,tvForget;
    private ImageView ivVerify,ivReturn;
    private Login login;
    private String key,coo,qqqcookie,s;

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
        ivReturn = findViewById(R.id.iv_lg_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getVerify();
        ivVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerify();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = null;
                try {
                    password = RSAEncrypt.publicEncrypt(etPassword.getText().toString().trim(),"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh2wXYiWpCnJCOzvzgjP6p4Y+C75jD92+SmIRPcYwWKyfqXtw1XBaGE/EVHb4ypS+Ugfh6+AAKOi9udBQClI5QIkoIGA8r3i6Ald9lKakeXrcBkm1M3Kgb4V8n1QENNfAyPizewJTF7VWxTtaPHWJzTf+f/njXV2mOiZY4nLcdEQs/GgXm9H++bFcS+OEj/MWjxdiB1jG5Fv1acSf9+hkpP2tZT7rloQW/BanmGUX0Awd4r6G5tcjDnMs9bx0s9jDslOngqxqMHj8GQ2JUBrd03OP+tk8IXlHl3qYPpi32CwTztGZvS9L797cHBBesg9rhrjkdGBae9sVZIG9OLRMZQIDAQAB\n" +
                            "V/123567: 111111");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String verify = etVerify.getText().toString().trim();
                login(username,password,verify);
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

    private Handler verifyHandler = new Handler(){
        public void handleMessage(Message mes){
            switch (mes.what){
                case 0:
                    Bitmap bitmap = (Bitmap) mes.obj;
                    ivVerify.setImageBitmap(bitmap);
            }}
    };

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new ReceivedCookiesInterceptor())//将请求图片验证码的cookie提取出来
            .addInterceptor(new AddCookiesInterceptor()) //将提取的cookie放入
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();


    public void login(String username, String password, String verify) {

        LoginApiSerice service = retrofit.create(LoginApiSerice.class);
        Call<ResponseBody> call = service.getLogin(username,password,verify);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    qqqcookie = response.headers().get("Set-cookie");
                    assert response.body() != null;
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.v("123",response.body().string());
                    if(jsonObject.getString("result").equals("success")){
                        Gson gson = new Gson();
                        login = gson.fromJson(jsonObject.getString("message"),Login.class);
                        loginSuccess();
                    }
                    if(jsonObject.getString("result").equals("failed")){
                        String s = jsonObject.getString("message");
                        loginError(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    loginError("访问服务器错误");
            }
        });
    }

    public void getVerify() {

        VerifyCodeApiSerice service = retrofit.create(VerifyCodeApiSerice.class);
        Call<ResponseBody> call = service.getVerifyCode();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                coo = response.headers().get("Set-Cookie");
                assert response.body() != null;
                InputStream is = response.body().byteStream();
                Bitmap bmp = BitmapFactory.decodeStream(is);
                Message message = new Message();
                message.what = 0;
                message.obj = bmp;
                verifyHandler.sendMessage(message);
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginError("访问服务器错误");
            }
        });
    }
    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            HashSet<String> preferences = (HashSet) LoginActivity.this.getSharedPreferences("config",
                    MODE_PRIVATE).getStringSet("cookie", null);
            if (preferences != null) {
                for (String cookie : preferences) {
                    builder.addHeader("Cookie", cookie);
                    Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
                }
            }
            return chain.proceed(builder.build());
        }
    }
    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }

                SharedPreferences.Editor config = LoginActivity.this.getSharedPreferences("config", MODE_PRIVATE)
                        .edit();
                config.putStringSet("cookie", cookies);
                config.apply();
            }

            return originalResponse;
        }
    }

}
//    public class AddCookiesInterceptor implements Interceptor {
//
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//            HashSet<String> preferences = (HashSet) LoginActivity.this.getSharedPreferences("config",
//                    MODE_PRIVATE).getStringSet("cookie", null);
//            if (preferences != null) {
//                for (String cookie : preferences) {
//                    builder.addHeader("Cookie", cookie);
//                    Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
//                }
//            }
//            return chain.proceed(builder.build());
//        }
//    }
//    public class ReceivedCookiesInterceptor implements Interceptor {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            okhttp3.Response originalResponse = chain.proceed(chain.request());
//
////            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                HashSet<String> cookies = new HashSet<>();
//
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//                if(LoginActivity.this.getSharedPreferences("config",
//                        MODE_PRIVATE).getStringSet("cookie", null)!=null){
//                    SharedPreferences.Editor config = LoginActivity.this.getSharedPreferences("config", MODE_PRIVATE)
//                            .edit();
//                    config.clear();
//                    config.apply();
//                }
//            if(LoginActivity.this.getSharedPreferences("config",
//                    MODE_PRIVATE).getStringSet("cookie", null)==null){
//                SharedPreferences.Editor config = LoginActivity.this.getSharedPreferences("config", MODE_PRIVATE)
//                        .edit();
//                config.putStringSet("cookie", cookies);
//                config.apply();
//
//}
////            }
//            return originalResponse;
//        }
//    }


