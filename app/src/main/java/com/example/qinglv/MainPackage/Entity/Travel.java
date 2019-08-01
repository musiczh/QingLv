package com.example.qinglv.MainPackage.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 游记预览的实体类
 */
public class Travel implements Serializable {
    private String collectionNum;

    private String nickName;

    private List<String> photo ;

    private int id;

    private String title;

    private String headPortrait;

    private int userId;

    private String shareNum;

    private String starNum;

    public void setCollectionNum(String collectionNum){
        this.collectionNum = collectionNum;
    }
    public String getCollectionNum(){
        return this.collectionNum;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getNickName(){
        return this.nickName;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setHeadPortrait(String headPortrait){
        this.headPortrait = headPortrait;
    }
    public String getHeadPortrait(){
        return this.headPortrait;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setShareNum(String shareNum){
        this.shareNum = shareNum;
    }
    public String getShareNum(){
        return this.shareNum;
    }
    public void setStarNum(String starNum){
        this.starNum = starNum;
    }
    public String getStarNum(){
        return this.starNum;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }


}
