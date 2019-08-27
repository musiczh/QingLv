package com.example.qinglv.MainPackage.Entity;

public class Comment {
    private int articleId;

    private String nickname;

    private int id;

    private String time;

    private String headPortrait;

    private int userId;

    private String content;

    private int starNum;

    public void setArticleId(int articleId){
        this.articleId = articleId;
    }
    public int getArticleId(){
        return this.articleId;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
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
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setStarNum(int starNum){
        this.starNum = starNum;
    }
    public int getStarNum(){
        return this.starNum;
    }
}
