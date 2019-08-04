package com.example.qinglv.UserPackage.Presenter;

import android.graphics.Bitmap;

import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Model.LoginModel;

public class LoginPresenter extends BasePresenter<ILoginContract.View> implements ILoginContract.Presenter{

    private ILoginContract.Model mModel;

    public LoginPresenter(){
        mModel = new LoginModel(this);
    }

    @Override
    public void login(String username, String password,String verify) {
        mModel.login(username, password,verify);
    }

    //显示或更改验证码图片
    @Override
    public Bitmap changeVerify(String url) {
        return mModel.verify(url);
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

}
