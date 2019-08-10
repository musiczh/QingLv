package com.example.qinglv.MainPackage.Entity;

import java.util.List;

public class TravelDetail {
    private int tabId;

    private String comment_num;

    private String collectionNum;

    private List<String> photo ;

    private String title;

    private int userId;

    private String shareNum;

    private String content;

    private String pageView;

    private String publishedTime;

    private String stick;

    private int id;

    private int starNum;

    public void setTabId(int tabId){
        this.tabId = tabId;
    }
    public int getTabId(){
        return this.tabId;
    }
    public void setComment_num(String comment_num){
        this.comment_num = comment_num;
    }
    public String getComment_num(){
        return this.comment_num;
    }
    public void setCollectionNum(String collectionNum){
        this.collectionNum = collectionNum;
    }
    public String getCollectionNum(){
        return this.collectionNum;
    }
    public void setPhoto(List<String> photo){
        this.photo = photo;
    }
    public List<String> getPhoto(){
        return this.photo;
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
    public void setShareNum(String shareNum){
        this.shareNum = shareNum;
    }
    public String getShareNum(){
        return this.shareNum;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setPageView(String pageView){
        this.pageView = pageView;
    }
    public String getPageView(){
        return this.pageView;
    }
    public void setPublishedTime(String publishedTime){
        this.publishedTime = publishedTime;
    }
    public String getPublishedTime(){
        return this.publishedTime;
    }
    public void setStick(String stick){
        this.stick = stick;
    }
    public String getStick(){
        return this.stick;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setStarNum(int starNum){
        this.starNum = starNum;
    }
    public int getStarNum(){
        return this.starNum;
    }

}
