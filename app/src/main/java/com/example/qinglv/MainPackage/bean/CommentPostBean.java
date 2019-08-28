package com.example.qinglv.MainPackage.bean;

public class CommentPostBean {
    private int articleId;

    private String content;

    public void setArticleId(int articleId){
        this.articleId = articleId;
    }
    public int getArticleId(){
        return this.articleId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }


}
