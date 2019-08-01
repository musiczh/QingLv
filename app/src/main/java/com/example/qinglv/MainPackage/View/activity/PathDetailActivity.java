package com.example.qinglv.MainPackage.View.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Presentor.PathDetailPresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPathDetail;
import com.example.qinglv.R;

import org.w3c.dom.Text;

import java.util.Objects;

public class PathDetailActivity extends AppCompatActivity implements IViewPathDetail{
    private WebView webView;
    private TextView textViewTime;
    private ImageView imageView;
    private Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_detail);
        toolbar = findViewById(R.id.toolBar_path_detail);
        webView = findViewById(R.id.webView_path_detail_content);
        textViewTime = findViewById(R.id.textView_path_detail_time);
        imageView = findViewById(R.id.imageView_path_detail_preview);


        Intent intent = getIntent();
        IPresenterPathDetail iPresenterPathDetail = new PathDetailPresenter(this);
        iPresenterPathDetail.init(intent.getIntExtra("id",1));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPath(Path path) {
        webView.loadDataWithBaseURL("",path.getContent(),"text/html","UTF-8","");
        textViewTime.setText(path.getDepositTime());
        toolbar.setTitle(path.getTitle());
        setSupportActionBar(toolbar);
        Glide.with(this).load(path.getPreview()).into(imageView);

    }

    @Override
    public void onError(String errorType) {
        Toast.makeText(this,errorType,Toast.LENGTH_SHORT).show();
    }
}
