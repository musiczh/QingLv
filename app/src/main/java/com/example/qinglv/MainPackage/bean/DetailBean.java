package com.example.qinglv.MainPackage.bean;

import java.util.List;

public class DetailBean<T> {
        private String result;
        private T message;

        public void setResult(String result) {
            this.result = result;
        }

        public String getResult() {
            return this.result;
        }

        public void setMessage(T message) {
            this.message = message;
        }

        public T getMessage() {
            return this.message;
        }

}
