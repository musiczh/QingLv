package com.example.qinglv.UserPackage.Model;

import com.example.qinglv.UserPackage.Contract.ILoginContract;

public class LoginModel implements ILoginContract.Model {

    private ILoginContract.Presenter mPresenter;

    public LoginModel(ILoginContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void login(String username, String password,String verify) {

    }

    @Override
    public void verify() {

    }


}
