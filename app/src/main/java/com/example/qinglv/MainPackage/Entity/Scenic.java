package com.example.qinglv.MainPackage.Entity;

/**
 * 风景预览的实体类
 */
public class Scenic {
    private int id;

    private String preview;

    private String title;

    private String location;

    private float score;

    private String spotIntroduction;

    private String trafficInformation;

    private int starNum;

    private int collectionNum;

    private int commentNum;

    private String depositTime;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPreview(String preview){
        this.preview = preview;
    }
    public String getPreview(){
        return this.preview;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setScore(float score){
        this.score = score;
    }
    public float getScore(){
        return this.score;
    }
    public void setCommentNum(int commentNum){
        this.commentNum = commentNum;
    }
    public int getCommentNum(){
        return this.commentNum;
    }
    public void setDepositTime(String depositTime){
        this.depositTime = depositTime;
    }
    public String getDepositTime(){
        return this.depositTime;
    }
    public void setSpotIntroduction(String spotIntroduction){
        this.spotIntroduction = spotIntroduction;
    }
    public String getSpotIntroduction(){
        return this.spotIntroduction;
    }
    public void setTrafficInformation(String trafficInformation){
        this.trafficInformation = trafficInformation;
    }
    public String getTrafficInformation(){
        return this.trafficInformation;
    }
    public void setStarNum(int starNum){
        this.starNum = starNum;
    }
    public int getStarNum(){
        return this.starNum;
    }
    public void setCollectionNum(int collectionNum){
        this.collectionNum = collectionNum;
    }
    public int getCollectionNum(){
        return this.collectionNum;
    }



}