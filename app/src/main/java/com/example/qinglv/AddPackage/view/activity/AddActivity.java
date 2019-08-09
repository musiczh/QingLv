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

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.qinglv.AddPackage.adapter.PhotoListAdapter;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.presenter.CommitNoteBasePresenter;
import com.example.qinglv.AddPackage.presenter.CommitNotePresenter;
import com.example.qinglv.AddPackage.view.InitGalleryFinal;
import com.example.qinglv.R;

import java.io.File;
import java.lang.reflect.Array;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private static  final String TAG = "AddActivity";


    public static RecyclerView mRecyclerView;

    public static List<PhotoInfo> list =null;
    public static Context mContext;
    private static volatile Activity mCurrentActivity;

    private ImageView mBackImage;
    private TextView mNoteTypeTv;
    private Button mCommitNoteBtn;
    private EditText mTitleEditText,mContentEditText;


    private CommitNoteBasePresenter presenter;
    private  String mNoteType = null;
    private  int mTabId;
    private  String mContent = null;
    private  String mTitle = null;
    private static int mId = 0;

    private List<File> files = new ArrayList<>();


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
        setCurrentActivity(this);          //获取当前活动的上下文
    }

    //初始化布局
    public void initalView(){
        mRecyclerView = findViewById(R.id.photo_list_rcyView);
        mBackImage = findViewById(R.id.back_button);
        mNoteTypeTv = findViewById(R.id.note_type_textView);
        mCommitNoteBtn = findViewById(R.id.commit_note_button);
        mTitleEditText = findViewById(R.id.title_editText);
        mContentEditText = findViewById(R.id.content_Auto_Text);
        //初始化RecyclerView
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mBackImage.setOnClickListener(this);
        mNoteTypeTv.setOnClickListener(this);
        mCommitNoteBtn.setOnClickListener(this);
        //获取上下文
        mContext = getBaseContext();
        //初始化图片选择器
        InitGalleryFinal.initGalleryFinal(this);
        //初始化RecyclerView
        PhotoListAdapter adapter = new PhotoListAdapter(AddActivity.this,list);
        mRecyclerView.setAdapter(adapter);

    }



    //接受上个活动销毁时返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mNoteType = data.getStringExtra("noteType");
        mTabId = data.getIntExtra("tabId",1);
        mNoteTypeTv.setText(mNoteType);
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
            case R.id.commit_note_button:
                //提交游记
                mTitle = mTitleEditText.getText().toString();
                mContent = mContentEditText.getText().toString();
                for (int i =0;i<list.size();i++){
                    File f = new File(list.get(i).getPhotoPath());
                    files.add(f);
                }
                //多文件上传的参数值
                List<MultipartBody.Part> photos = new ArrayList<>();
                photos = filesToMultipartBodyParts(files);



                //给参数赋值
                Map<String,RequestBody> params = new HashMap<>();
                params.put("title",toRequestBody(mTitle));
                params.put("content",toRequestBody(mContent));
                params.put("id",toRequestBody(String.valueOf(mId)));
                params.put("tabId",toRequestBody(String.valueOf(mTabId)));

                presenter = new CommitNotePresenter();
                ((CommitNotePresenter) presenter).commitNote(params,photos);
                presenter.attachView(this);


                Log.d("AddActivity","----"+mId+mTabId);
                Log.d("id","------"+params.get("id"));
                Log.d("tabId","------"+params.get("tabId").toString());
                Log.d("photos","------"+photos.size());
                Log.d("title","-------"+params.get("title"));
                Log.d("content","-------"+mContent);
                break;
        }
    }


    //图片文件转为多文件上传参数的方法
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("aFile", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    //多参数+多文件上传key值转为参数的方法
    public static RequestBody toRequestBody(String value){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),value);
        return requestBody;
    }




    public void  addParams(){

        for (int i =0;i<list.size();i++){
            File f = new File(list.get(i).getPhotoPath());
            files.add(f);
        }
        //多文件上传的参数值
        List<MultipartBody.Part> photos = new ArrayList<>();
        photos = filesToMultipartBodyParts(files);



        //给参数赋值
        Map<String,RequestBody> params = new HashMap<>();
        params.put("title",toRequestBody(mTitle));
        params.put("content",toRequestBody(mContent));
        params.put("id",toRequestBody(String.valueOf(mId)));
        params.put("tabId",toRequestBody(String.valueOf(mTabId)));

        for(int i =0;i<files.size();i++){

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), files.get(i));
            MultipartBody.Part part = MultipartBody.Part.createFormData("aFile", files.get(i).getName(), requestBody);


        }


        RequestBody body = new MultipartBody.Builder()
                .addFormDataPart("id", String.valueOf(mId))
                .addFormDataPart("tabId", String.valueOf(mTabId))
                .addFormDataPart("title",mTitle)
                .addFormDataPart("content",mContent)
                .build();

        presenter = new CommitNotePresenter();
        ((CommitNotePresenter) presenter).commitNote(params,photos);
        presenter.attachView(this);


        Log.d("AddActivity","----"+mId+mTabId);
        Log.d("id","------"+params.get("id"));
        Log.d("tabId","------"+params.get("tabId").toString());
        Log.d("photos","------"+photos.size());
        Log.d("title","-------"+params.get("title"));
        Log.d("content","-------"+mContent);
    }
}
