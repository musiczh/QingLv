package com.example.qinglv.MainPackage.View.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Presentor.ScenicDetailPresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;
import com.example.qinglv.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

public class ScenicDetailActivity extends AppCompatActivity implements IViewDetail<Scenic> {
    private ProgressBar progressBar;
    private WebView webViewContent;
    private WebView webViewTraffic;
    private ImageView imageView;
    private TextView textViewLocation;
    private TextView textViewTime;
    private Toolbar toolbar;
    private IPresenterDetail iPresenterDetail;
    private ImageView heart;
    private ImageView collection;
    private boolean isHeart;
    private boolean isCollected;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scenic);
        final Intent intent = getIntent();


        //控件属性初始化
        toolbar = findViewById(R.id.toolBar_scenic_detail);
        heart = findViewById(R.id.imageView_detail_scenic_heart);
        collection = findViewById(R.id.imageView_detail_scenic_collection);
        webViewContent = findViewById(R.id.webView_share_content);
        webViewTraffic = findViewById(R.id.webView_scenic_detail_traffic);
        imageView = findViewById(R.id.imageView_scenic_detail_preview);
        textViewLocation = findViewById(R.id.textView_scenic_detail_location);
        textViewTime = findViewById(R.id.textView_scenic_detail_time);
        progressBar = findViewById(R.id.progressBar_scenic_detail);


        //设置支持http协议图片混合的数据
        supportHttpMix(webViewContent);
        supportHttpMix(webViewTraffic);

        //悬浮按钮设置监听
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_detail_scenic);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ScenicDetailActivity.this,CommentActivity.class);
                intent1.putExtra("id",intent.getIntExtra("id",1));
                intent1.putExtra("articleType",CommentActivity.SCENIC);

                startActivity(intent1);
            }
        });

        //刷新数据
        iPresenterDetail = new ScenicDetailPresenter();
        ((ScenicDetailPresenter) iPresenterDetail).attachView(this);
        iPresenterDetail.init(intent.getIntExtra("id",1));


    }

    //顶部返回按钮的监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //获取到数据时进行对控件赋值
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setDetail(Scenic detail) {
        if (detail != null) {
            webViewContent.loadDataWithBaseURL("", getNewsContent(detail.getSpotIntroduction()), "text/html", "UTF-8", "");
            webViewTraffic.loadDataWithBaseURL("", detail.getTrafficInformation(), "text/html", "UTF-8", "");
            textViewLocation.setText(detail.getLocation());
            textViewTime.setText(detail.getDepositTime());
            toolbar.setTitle(detail.getTitle());
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            Glide.with(this).load(detail.getPreview()).into(imageView);
        }else{
            onError("好像出了一点小问题");
        }
        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }
    }

    //错误的时候显示一个toast
    @Override
    public void onError(String errorType) {
        Toast.makeText(this,errorType,Toast.LENGTH_SHORT).show();
        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void setHeart(boolean isHeart) {
        this.isHeart = isHeart;
        if (isHeart) heart.setImageResource(R.drawable.img_heart_red);
        else heart.setImageResource(R.drawable.img_heart);
    }

    @Override
    public void setStar(boolean isStar) {
        this.isCollected = isStar;
        if (isStar) collection.setImageResource(R.drawable.img_star_yellow);
        else  collection.setImageResource(R.drawable.img_star);
    }

    //设置可混合http和https两种协议的网址数据
    @SuppressLint("SetJavaScriptEnabled")
    private void supportHttpMix(WebView webView){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setJavaScriptEnabled(true);//启用js
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
    }

    //把html数据里面的图片放缩到适合屏幕的尺寸
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
        ((ScenicDetailPresenter) iPresenterDetail).detachView();
    }
}
