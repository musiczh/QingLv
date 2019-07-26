package com.example.qinglv.MainPackage.Entity;

/**
 * 美食预览图的实体类
 */
public class Food {
    private String id;

    private String preview;

    private String title;

    private String content;

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
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setDepositTime(String depositTime){
        this.depositTime = depositTime;
    }
    public String getDepositTime(){
        return this.depositTime;
    }

}