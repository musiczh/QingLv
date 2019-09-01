package com.example.qinglv.UserPackage.Presenter;

import android.content.Context;

import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Model.LoginModel;
import com.example.qinglv.UserPackage.View.LoginActivity;

public class LoginPresenter extends BasePresenter<LoginActivity> implements ILoginContract.Presenter{

    private ILoginContract.Model mModel;
    private Context context;

    public LoginPresenter(){
        mModel = new LoginModel(this);
    }

    @Override
    public void login(String username, String password,String verify) {
        mModel.login(username, password,verify);
    }

//    @Override
//    public String getKey() {
//        return mModel.getKey();
//    }

    @Override
    public byte[] getVerify() {
        return mModel.getVerify();
    }

    @Override
    public void setCon(Context con) {
        context = con;
    }


    @Override
    public void loginSuccess() {
        if (isAttachView()){
            getMvpView().loginSuccess();
        }
    }

    @Override
    public void loginError(String msg) {
        if (isAttachView()){
            getMvpView().loginError(msg);
        }
    }

    @Override
    public Context getCon() {
        return context;
    }

//    @Override
//    public String getCookie() {
//        return mModel.getCookie();
//    }

    @Override
    public LoginActivity getMvpView() {
        return super.getMvpView();
    }
}
