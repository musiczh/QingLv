package com.example.qinglv.UserPackage.Contract;

import android.graphics.Bitmap;

public class ILoginContract {
    public interface View{
        void loginSuccess();
        void loginError(String msg);
    }

    public interface Presenter {
        //View
        void login(String username,String password,String verify);
        String getKey();
        //model
        void loginSuccess();
        void loginError(String msg);
    }

    public interface Model{
        void login(String username,String password,String verify);
        String getKey();
    }
}