package com.example.qinglv.MainPackage.View.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

public class ScenicDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_detail);
        Toolbar toolbar = findViewById(R.id.toolBar_scenic_detail);



        Scenic scenic = (Scenic) Objects.requireNonNull(getIntent().getExtras()).getSerializable("scenic");
        WebView webViewContent = findViewById(R.id.webView_share_content);
        WebView webViewTraffic = findViewById(R.id.webView_scenic_detail_traffic);
        ImageView imageView = findViewById(R.id.imageView_scenic_detail_preview);
        TextView textViewLocation = findViewById(R.id.textView_scenic_detail_location);
        TextView textViewTime = findViewById(R.id.textView_scenic_detail_time);
        assert scenic != null;
        webViewContent.loadDataWithBaseURL("",getNewsContent(scenic.getSpotIntroduction()),"text/html","UTF-8","");
        webViewTraffic.loadDataWithBaseURL("",scenic.getTrafficInformation(),"text/html","UTF-8","");
        textViewLocation.setText(scenic.getLocation());
        textViewTime.setText(scenic.getDepositTime());
        toolbar.setTitle(scenic.getTitle());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(scenic.getPreview()).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

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
}
