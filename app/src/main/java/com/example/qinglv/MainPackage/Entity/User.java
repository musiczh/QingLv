package com.example.qinglv.MainPackage.Entity;

public class User {
    private String userName;
    private String headPortrait;

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getUserName() {
        return userName;
    }
}
