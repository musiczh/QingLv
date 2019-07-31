package com.example.qinglv.MainPackage.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 游记预览的实体类
 */
public class Travel implements Serializable {
    private int id;

    private String title;

    private int userId;

    private List<String> photo;

    private String starNum;

    //private String nickname;

    private String headPortrait;

    private String publishedTime;

    private String content;

    private String collectionNum;

    private String comment_num;



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
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
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
    /*public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }*/
    public void setHeadPortrait(String headPortrait){
        this.headPortrait = headPortrait;
    }
    public String getHeadPortrait(){
        return this.headPortrait;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getContent() {
        return content;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public String getComment_num() {
        return comment_num;
    }

    public String getPublishedTime() {
        return publishedTime;
    }
}
