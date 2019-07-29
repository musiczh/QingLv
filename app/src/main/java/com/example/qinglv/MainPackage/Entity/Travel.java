package com.example.qinglv.MainPackage.Entity;

import java.util.List;

/**
 * 游记预览的实体类
 */
public class Travel {
    private String id;

    private String title;

    private String userId;

    private List<String> photo;

    private String starNum;

    private String nickname;

    private String headPortrait;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setPhoto(List<String> photo){
        this.photo = photo;
    }
    public List<String> getPhoto(){
        return this.photo;
    }
    public void setStarNum(String starNum){
        this.starNum = starNum;
    }
    public String getStarNum(){
        return this.starNum;
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

}
