package com.example.qinglv.MainPackage.View.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qinglv.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent intent = getIntent();
        String url = intent.getStringExtra("image");
        if (url!=null)
        Glide.with(this).load(url).into(((ImageView) findViewById(R.id.imageView_bid_activity)));

    }
}
