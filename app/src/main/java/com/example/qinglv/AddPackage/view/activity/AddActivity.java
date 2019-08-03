package com.example.qinglv.AddPackage.view.activity;



import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.qinglv.AddPackage.adapter.PhotoListAdapter;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.view.InitGalleryFinal;
import com.example.qinglv.R;

import java.util.List;


import cn.finalteam.galleryfinal.model.PhotoInfo;




public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private static  final String TAG = "AddActivity";


    public static RecyclerView mRecyclerView;

    public static List<PhotoInfo> list =null;
    public static Context mContext;
    private static volatile Activity mCurrentActivity;

    private ImageView mBackImage;
    private TextView mNoteTypeTv;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //初始化界面
        initalView();
    }
    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    private void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentActivity(this);
    }

    public void initalView(){
        mRecyclerView = findViewById(R.id.photo_list_rcyView);
        mBackImage = findViewById(R.id.back_button);
        mNoteTypeTv = findViewById(R.id.note_type_textView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mBackImage.setOnClickListener(this);
        mNoteTypeTv.setOnClickListener(this);
        //获取上下文
        mContext = getBaseContext();
        //初始化图片选择器
        InitGalleryFinal.initGalleryFinal(this);
        //初始化RecyclerView
        PhotoListAdapter adapter = new PhotoListAdapter(AddActivity.this,list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    //接受上个活动销毁时返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String noteType = data.getStringExtra("noteType");
        mNoteTypeTv.setText(noteType);
        Log.d("noteType",""+noteType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case  R.id.note_type_textView:
              Intent intent = new Intent(AddActivity.this, NoteTypeActivity.class);
              startActivityForResult(intent,0);
                break;
        }
    }





}
