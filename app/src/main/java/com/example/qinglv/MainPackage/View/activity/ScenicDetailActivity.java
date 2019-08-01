package com.example.qinglv.MainPackage.View.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.R;

import java.util.Objects;

public class ScenicDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_detail);

        Scenic scenic = (Scenic) Objects.requireNonNull(getIntent().getExtras()).getSerializable("scenic");
        WebView webView = findViewById(R.id.webView_share_hdhd);
        assert scenic != null;
        String s = scenic.getTrafficInformation();
        webView.loadDataWithBaseURL("",s,"text/html","UTF-8","");
    }
}
