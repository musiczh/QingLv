package com.example.qinglv.UserPackage.Presenter;

import com.example.qinglv.UserPackage.Contract.ILoginContract;
import com.example.qinglv.UserPackage.Model.LoginModel;

public class LoginPresenter extends BasePresenter<ILoginContract.View> implements ILoginContract.Presenter{

    private ILoginContract.Model mModel;

    public LoginPresenter(){
        mModel = new LoginModel(this);
    }

    @Override
    public void login(String username, String password) {
        mModel.login(username, password);
    }

    @Override
    public void register(String username, String password) {
        mModel.register(username, password);
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
    public void registerSuccess() {
        if (isAttachView()){
            getMvpView().registerSuccess();
        }
    }

    @Override
    public void registerError(String msg) {
        if (isAttachView()){
            getMvpView().registerError(msg);
        }
    }
}
