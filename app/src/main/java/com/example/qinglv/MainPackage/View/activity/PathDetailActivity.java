package com.example.qinglv.MainPackage.View.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Presentor.PathDetailPresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;
import com.example.qinglv.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

public class PathDetailActivity extends AppCompatActivity implements IViewDetail<Path> {
    private WebView webView;
    private TextView textViewTime;
    private ImageView imageView;
    private Toolbar toolbar;
    private IPresenterDetail iPresenterDetail;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;


    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_path);
        toolbar = findViewById(R.id.toolBar_path_detail);
        webView = findViewById(R.id.webView_path_detail_content);
        textViewTime = findViewById(R.id.textView_path_detail_time);
        imageView = findViewById(R.id.imageView_path_detail_preview);


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setJavaScriptEnabled(true);//启用js
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示

        progressBar = findViewById(R.id.progressBar_path_detail);
        coordinatorLayout = findViewById(R.id.coordinatorLayout_path_detail);
        //coordinatorLayout.setVisibility(View.GONE);


        final Intent intent = getIntent();//获取intent

        //悬浮按钮设置监听
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_detail_path);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PathDetailActivity.this,CommentActivity.class);
                intent1.putExtra("id",intent.getIntExtra("id",1));
                intent1.putExtra("articleType",CommentActivity.PATH);
                startActivity(intent1);
            }
        });

        //初始化数据
        iPresenterDetail = new PathDetailPresenter();
        ((PathDetailPresenter) iPresenterDetail).attachView(this);
        iPresenterDetail.init(intent.getIntExtra("id",1));

    }

    //顶部toolBar返回按钮的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //mvp接口方法。将获取到的数据传入
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setDetail(Path path) {
        String s = getNewsContent(path.getContent());
        webView.loadDataWithBaseURL("",s,"text/html","UTF-8","");
        textViewTime.setText(path.getDepositTime());
        toolbar.setTitle(path.getTitle());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(path.getPreview()).into(imageView);

        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }

        if (coordinatorLayout.getVisibility() == View.GONE){
            coordinatorLayout.setVisibility(View.VISIBLE);
        }

    }

    //mvp接口方法，错误的时候给出提示
    @Override
    public void onError(String errorType) {
        Toast.makeText(this,errorType,Toast.LENGTH_SHORT).show();
        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }
        if (coordinatorLayout.getVisibility() == View.GONE){
            coordinatorLayout.setVisibility(View.VISIBLE);
        }
    }

    //运用jsoup框架把html数据中的图片以正确的格式显示
    String getNewsContent(String htmlStr){
        try{
            Document doc = Jsoup.parse(htmlStr);
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements){
                element.attr("width","100%").attr("height","auto");
            }
            return doc.toString();
        }catch (Exception e){
            return htmlStr;
        }
    }

    //销毁时同时解除引用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((PathDetailPresenter) iPresenterDetail).detachView();
    }

    @Override
    public void setHeart(boolean isHeart) {

    }

    @Override
    public void setStar(boolean isStar) {

    }
}
