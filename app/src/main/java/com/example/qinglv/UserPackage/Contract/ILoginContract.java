package com.example.qinglv.UserPackage.Contract;

public class ILoginContract {
    public interface View{
        void loginSuccess();
        void loginError(String msg);
    }

    public interface Presenter {
        //View
        void login(String username,String password,String verify);
        void changeVerify();
        //model
        void loginSuccess();
        void loginError(String msg);
    }

    public interface Model{
        void login(String username,String password,String verify);
        void verify();
    }
}
