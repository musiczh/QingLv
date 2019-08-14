package com.example.qinglv.UserPackage.Contract;

import android.content.Context;

public class ILoginContract {
    public interface View{
        void loginSuccess();
        void loginError(String msg);
    }

    public interface Presenter {
        //View
        void login(String username,String password,String verify);
//        String getKey();
        byte[] getVerify();
        void setCon(Context con);
        //model
        void loginSuccess();
        void loginError(String msg);
        Context getCon();
//        String getCookie();
    }

    public interface Model{
        void login(String username,String password,String verify);
//        String getKey();
        byte[] getVerify();
//        String getCookie();
    }
}
