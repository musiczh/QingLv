package com.example.qinglv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                start();
                LunchActivity.this.finish();

            }
        }).start();
    }

    private void start(){
        Intent intent = new Intent(LunchActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
