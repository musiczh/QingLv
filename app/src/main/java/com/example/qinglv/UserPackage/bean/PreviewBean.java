package com.example.qinglv.UserPackage.bean;

import java.util.List;

/**
 * 获得预览数据的bean类，这里根据不同的预览数据设置不同的泛型
 *
 * @param <T>
 */
public class PreviewBean<T> {
    private String result;

    private List<T> message ;

    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return this.result;
    }
    public void setMessage(List<T> message){
        this.message = message;
    }
    public List<T> getMessage(){
        return this.message;
    }

}
