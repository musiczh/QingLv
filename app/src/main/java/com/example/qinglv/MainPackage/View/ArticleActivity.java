package com.example.qinglv.MainPackage.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qinglv.R;
import com.sackcentury.shinebuttonlib.ShineButton;


public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ShineButton shineButton = findViewById(R.id.shineButton_article_good);
        shineButton.init(this);
    }
}
