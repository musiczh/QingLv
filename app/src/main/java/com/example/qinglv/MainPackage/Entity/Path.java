package com.example.qinglv.MainPackage.Entity;

import java.io.Serializable;

/**
 * 路线预览的实体类
 */
public class Path implements Serializable {
    private int id;

    private String preview;

    private String title;

    private String introduction;

    private String content;

    private int commentNum;

    private int starNum;

    private int collectionNum;

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
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
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

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getStarNum() {
        return starNum;
    }
}
