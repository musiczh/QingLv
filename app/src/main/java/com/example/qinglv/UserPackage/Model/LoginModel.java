package com.example.qinglv.UserPackage.Model;

import android.util.Log;

import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Entity.Login;
import com.example.qinglv.UserPackage.IApiSerice.LoginApiSerice;
import com.example.qinglv.UserPackage.IApiSerice.VerifyCodeApiSerice;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.example.qinglv.util.StaticQuality.BASE_URL;

public class LoginModel implements ILoginContract.Model {

    private ILoginContract.Presenter mPresenter;
    private Login login;
    private String key,coo,qqqcookie,s;
    private byte[] bytes;
    public LoginModel(ILoginContract.Presenter presenter){
        mPresenter = presenter;
    }

    OkHttpClient client = new OkHttpClient.Builder()

//            .addInterceptor(new AddCookiesInterceptor()) //这部分
//            .addInterceptor(new ReceivedCookiesInterceptor())//这部分
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();

    @Override
    public void login(String username, String password, String verify) {

        LoginApiSerice service = retrofit.create(LoginApiSerice.class);
        Call<ResponseBody> call = service.getLogin(username,password,verify);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            qqqcookie = response.headers().get("set-cookie");
                            assert response.body() != null;
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

//    public String getKey(){
//
//        RetrofitManager.getInstance().createRs(KeyApiSerice.class)
//                .getKey()
//                .enqueue(new Callback<ResponseBody>() {
//
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        assert response.body() != null;
//                        try {
//                            JSONObject jsonObject =new JSONObject(response.body().string());
//                            key = jsonObject.getString("message");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
//        return key;
//    }

//    public String getCookie(){
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url(BASE_URL + "user/verifyCode/")
//                        .build();
//                okhttp3.Response response = null;
//                try {
//                    response = client.newCall(request).execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            Headers headers =response.headers();
//                List<String> cookies = headers.values("Set-Cookie");
//                String session = cookies.get(0);
//                Log.e("TAG", "onResponse-size: " + cookies);
//                s = session.substring(0, session.indexOf(";"));
//                Log.e("TAG", "session is :" + s);
//            }
//        }).start();
//        return s;
//    }

    @Override
    public byte[] getVerify() {
        VerifyCodeApiSerice service = retrofit.create(VerifyCodeApiSerice.class);
        Call<ResponseBody> call = service.getVerifyCode();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        coo = response.headers().get("set-cookie");
                        assert response.body() != null;
                        InputStream is = response.body().byteStream();
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024*4];
                        int n = 0;
                        while (true) {
                            try {
                                if (-1 == (n = is.read(buffer))) break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            output.write(buffer, 0, n);
                        }
                       bytes = output.toByteArray();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

        return bytes;
    }

//    public class ReceivedCookiesInterceptor implements Interceptor {
//        List<String> cookies;
//        @NotNull
//        @Override
//        public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//            okhttp3.Response originalResponse = chain.proceed(chain.request());
//
//            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//                cookieDB = new CookieDB(mPresenter.getCon(),"day.db",null,1);
//                SQLiteDatabase db = cookieDB.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("cookie", cookies.get(0));
//                db.insert("Book",null,values);
//                db.close();
////                SharedPreferences.Editor config = mPresenter.getCon().getSharedPreferences("config", mPresenter.getCon().MODE_PRIVATE)
////                        .edit();
////                config.putStringSet("cookie", cookies);
////                config.commit();
//            }
//
//            return originalResponse;
//        }
//    }
//    public class AddCookiesInterceptor implements Interceptor {
//
//        @NotNull
//        @Override
//        public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//            String cookie = null;
//            Request.Builder builder = chain.request().newBuilder();
//            cookieDB = new CookieDB(mPresenter.getCon(),"day.db",null,1);
//            SQLiteDatabase db = cookieDB.getWritableDatabase();
//            Cursor cursor = db.query("Book",null,null,
//                    null,null,null,null);
//            if (cursor.moveToFirst()) {
//                do{
//                    if(!cursor.getString(cursor.getColumnIndex("dayData")).equals("")){
//                        cookie =cursor.getString(cursor.getColumnIndex("dayData"));
//                        cursor.close();
//                    }
//                }while (cursor.moveToNext());
//            }
//            db.close();
//
//            if(cookie != null){
//                builder.addHeader("Cookie", cookie);
//                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
//            }
//                    return chain.proceed(builder.build());
//        }
//    }


//
//    public class AddCookiesInterceptor implements Interceptor {
//
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//            HashSet<String> preferences = (HashSet) mPresenter.getCon().getSharedPreferences("config",
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
//            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                HashSet<String> cookies = new HashSet<>();
//
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//
//                SharedPreferences.Editor config = mPresenter.getCon().getSharedPreferences("config", MODE_PRIVATE)
//                        .edit();
//                config.putStringSet("cookie", cookies);
//                config.apply();
//            }
//
//            return originalResponse;
//        }
//    }

    }


