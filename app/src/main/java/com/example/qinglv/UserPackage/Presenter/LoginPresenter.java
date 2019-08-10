package com.example.qinglv.UserPackage.Presenter;

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

    @Override
    public String getKey() {
        return mModel.getKey();
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
