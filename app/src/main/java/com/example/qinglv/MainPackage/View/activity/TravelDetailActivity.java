package com.example.qinglv.MainPackage.View.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Adapter.ViewPagerAdapterImage;
import com.example.qinglv.MainPackage.Entity.TravelDetail;
import com.example.qinglv.MainPackage.Presentor.TravelDetailPresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;
import com.example.qinglv.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TravelDetailActivity extends AppCompatActivity implements IViewDetail<TravelDetail> {
    private Banner banner;
    private TextView textViewContent;
    private TextView textViewTime;
    private ProgressBar progressBar;
    private ImageView imageViewStar;
    private List<String> imageList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_travel);

        //控件初始化
        Toolbar toolbar = findViewById(R.id.toolbar_detail_travel);

        banner = findViewById(R.id.banner_detail_travel_photo);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(TravelDetailActivity.this,ImageActivity.class);
                if (imageList!=null) {
                    intent.putExtra("image", imageList.get(position - 1));
                }
                startActivity(intent);
            }
        });
        banner.isAutoPlay(false);

        ImageView imageViewAuthor = findViewById(R.id.imageView_detail_travel_author);
        imageViewStar = findViewById(R.id.imageView_travel_detail_heart);
        textViewContent = findViewById(R.id.textView_detail_travel_content);
        TextView textViewNickName = findViewById(R.id.textView_detail_travel_author);
        textViewTime = findViewById(R.id.textView_detail_travel_time);
        progressBar = findViewById(R.id.progressBar_travel_detail);
        IPresenterDetail iPresenterDetail = new TravelDetailPresenter();

        final Intent intent = getIntent();//获取intent中的id



        //悬浮按钮设置监听
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_detail_travel);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TravelDetailActivity.this,CommentActivity.class);
                intent1.putExtra("id",intent.getIntExtra("id",1));
                intent1.putExtra("articleType",CommentActivity.TRAVEL);

                startActivity(intent1);
            }
        });

        //给toolBar设置标题,用户头像以及用户名字
        textViewNickName.setText(intent.getStringExtra("nickName"));
        if (intent.getStringExtra("headPortrait") != null)
        Glide.with(this).load(intent.getStringExtra("headPortrait")).into(imageViewAuthor);
        toolbar.setTitle(intent.getStringExtra("tittle"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //访问网络刷新数据
        ((TravelDetailPresenter) iPresenterDetail).attachView(this);
        iPresenterDetail.init(intent.getIntExtra("id",1));




    }

    //内部类banner图片加载
    class GlideImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


    //顶部toolBar返回按钮的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    //mvp接口方法。将获取到的数据传入
    @Override
    public void setDetail(TravelDetail travelDetail) {
        textViewTime.setText(travelDetail.getPublishedTime());
        textViewContent.setText(travelDetail.getContent());
        imageList = travelDetail.getPhoto();

        if (imageList != null && (!imageList.isEmpty())) {
            banner.setImages(imageList);
            banner.start();
        }else{
            List<Integer> list = new ArrayList<>();
            list.add(R.drawable.img_no_img);
            banner.setImages(list);
            banner.start();
        }

        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }

    }

    //mvp接口方法，错误的时候给出提示
    @Override
    public void onError(String errorType) {
        Toast.makeText(this,errorType,Toast.LENGTH_SHORT).show();
        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void setHeart(boolean isHeart) {
        if (imageViewStar == null) {
            imageViewStar = findViewById(R.id.imageView_travel_detail_heart);
        }
        if (imageViewStar == null) {
            //Toast.makeText(this, "kongde", Toast.LENGTH_SHORT).show();
        }

        //if (isHeart) imageViewStar.setImageResource(R.drawable.img_heart_red);
        //else imageViewStar.setImageResource(R.drawable.img_heart);
    }

    @Override
    public void setCollection(boolean isStar) {

    }
}
