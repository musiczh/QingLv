package com.example.qinglv.MainPackage.Entity;

/**
 * 路线预览的实体类
 */
public class Path {
    private String id;

    private String preview;

    private String title;

    private String introduction;

    private String commentNum;

    private String depositTime;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
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
    public void setCommentNum(String commentNum){
        this.commentNum = commentNum;
    }
    public String getCommentNum(){
        return this.commentNum;
    }
    public void setDepositTime(String depositTime){
        this.depositTime = depositTime;
    }
    public String getDepositTime(){
        return this.depositTime;
    }

}
