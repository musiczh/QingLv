package com.example.qinglv.AddPackage.view.activity;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
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
import android.widget.Toast;


import com.example.qinglv.AddPackage.adapter.PhotoListAdapter;
import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.presenter.CommitNoteBasePresenter;
import com.example.qinglv.AddPackage.presenter.CommitNotePresenter;
import com.example.qinglv.AddPackage.view.InitGalleryFinal;
import com.example.qinglv.MainActivity;
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.R;

import java.io.File;
import java.lang.reflect.Array;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.qinglv.AddPackage.view.ShowPopupWindow.mOnHanlderResultCallback;


public class AddActivity extends AppCompatActivity implements View.OnClickListener , ICommitNoteContract.IView {

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
        PhotoListAdapter adapter = new PhotoListAdapter(AddActivity.this, list);
        mRecyclerView.setAdapter(adapter);

    }



    //接受上个活动销毁时返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:
                mNoteType = data.getStringExtra("noteType");
                mTabId = data.getIntExtra("tabId",1);
                mNoteTypeTv.setText(mNoteType);
                break;
            case 2:
                String defaultTv = data.getStringExtra("default");
                mNoteTypeTv.setText(defaultTv);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                //返回
                list =null;
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

                if(list!=null)
                for (int i =0;i<list.size();i++){
                    File f = new File(list.get(i).getPhotoPath());
                    files.add(f);
                }
                //多文件上传的参数值
                List<MultipartBody.Part> photos = new ArrayList<>();
                photos = filesToMultipartBodyParts(files,"photo");
                //有照片时的参数
                Map<String,RequestBody> params = new HashMap<>();
                params.put("title",toRequestBody(mTitle));
                params.put("content",toRequestBody(mContent));
                params.put("id",toRequestBody(String.valueOf(mId)));
                params.put("tabId",toRequestBody(String.valueOf(mTabId)));

                //无照片时的参数
                FormBody body = new FormBody.Builder()
                        .add("id", String.valueOf(mId))
                        .add("tabId", String.valueOf(mTabId))
                        .add("content",mContent)
                        .add("title",mTitle)
                        .build();
                presenter = new CommitNotePresenter();
                if(mTitle!=null&mContent!=null&mNoteType!=null)                //判断必要的参数是否填入
                {
//                    if (list != null) {
//                        ((CommitNotePresenter) presenter).commitNote(body);
//                    } else {
                        ((CommitNotePresenter) presenter).commitPhotoNote(params, photos);
//                    }
                }else {
                    Toast.makeText(AddActivity.this,"请填入必要的信息",Toast.LENGTH_LONG).show();
                }
                presenter.attachView(this);
                break;
        }
    }

    //重写返回，清空照片
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        list = null;
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 将文件路径数组封装为{@link List<MultipartBody.Part>}
     * @param files
     * @param key  对应请求正文中name的值。目前服务器给出的接口中，所有图片文件使用<br>同一个name值，实际情况中有可能需要多个
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files,String key) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    //将参数封装成requestBody形式上传参数
    public static RequestBody toRequestBody(String value){
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"),value);
        return requestBody;
    }


    @Override
    public void setErrorToast(String string) {
        Toast.makeText(AddActivity.this,string,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        dialogBox();
    }

    //弹出对话框
    private void dialogBox() {
        AlertDialog.Builder bb = new AlertDialog.Builder(this);
        bb.setMessage("笔记已成功发布");
        bb.setTitle("提示");
        bb.setCancelable(true);
        bb.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list = null;
                finish();
            }
        });

        bb.show();
    }

    //权限申请后的处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                GalleryFinal.openGalleryMuti(1001, InitGalleryFinal.functionConfig, mOnHanlderResultCallback);
                break;
            case 0:
                GalleryFinal.openCamera(1002, InitGalleryFinal.functionConfig, mOnHanlderResultCallback);
                break;
        }

    }
}
