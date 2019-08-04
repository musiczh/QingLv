package com.example.qinglv.UserPackage.Entity;

public class Login {

    private String id;

    private String userName;

    private String nickname;

    private String headPortrait;

    private String address;

    private String birthday;

    private String signature;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setHeadPortrait(String headPortrait){
        this.headPortrait = headPortrait;
    }
    public String getHeadPortrait(){
        return this.headPortrait;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public String getBirthday(){
        return this.birthday;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getSignature(){
        return this.signature;
    }

}
