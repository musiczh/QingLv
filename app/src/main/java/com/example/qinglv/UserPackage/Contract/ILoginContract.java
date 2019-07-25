package com.example.qinglv.UserPackage.Contract;

public class ILoginContract {
    public interface View{
        void loginSuccess();
        void loginError(String msg);
        void registerSuccess();
        void registerError(String msg);
    }

    public interface Presenter {
        //View
        void login(String username,String password);
        void register(String username,String password);
        //model
        void loginSuccess();
        void loginError(String msg);
        void registerSuccess();
        void registerError(String msg);
    }

    public interface Model{
        void login(String username,String password);
        void register(String username,String password);
    }
}
