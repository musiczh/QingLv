package com.example.qinglv.MainPackage.inter.iApiMvp;

import com.example.qinglv.MainPackage.Entity.Comment;

import java.util.List;

public interface IViewComment {
    void setComment(List<Comment> list, boolean isMore);
    void setToast(String stringToast);
}
