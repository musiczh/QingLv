package com.example.qinglv.AddPackage.view;



import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinglv.AddPackage.adapter.PhotoListAdapter;
import com.example.qinglv.R;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

import static com.example.qinglv.AddPackage.view.Util.initGalleryFinal;


public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private static  final String TAG = "AddActivity";
    private static  final int REQUEST_CODE_GALLERY = 1;  //打开相册
    private static  final int REQUEST_CODE_CAMERA = 2;    //使用拍照


    private ImageButton mAddPcitureBtn;
    private PopupWindow mPopWindow;
    private TextView photoTv;
    private TextView photographTv;
    private TextView cancelTv;

    private RecyclerView mRecyclerView;
    private ListView listView;





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


    public void initalView(){
        mAddPcitureBtn = findViewById(R.id.add_picture_button);


        mRecyclerView = findViewById(R.id.photo_list_rcyView);
//        listView = findViewById(R.id.photo_list_rcyView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAddPcitureBtn.setOnClickListener(this);

        //初始化图片选择器
        initGalleryFinal(this);
    }

    //弹出菜单选项
    public void showPopupWindow(){
        //设置contentView
        View contentView = LayoutInflater.from(AddActivity.this).inflate(R.layout.photo_popuplayout,null);
        mPopWindow = new PopupWindow(contentView, ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击事件
        photoTv = contentView.findViewById(R.id.pop_photo);
        photographTv = contentView.findViewById(R.id.pop_photograph);
        cancelTv = contentView.findViewById(R.id.pop_cancel);
        photoTv.setOnClickListener(this);
        photographTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);
        mPopWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);

        //显示contentView
        View rootView = LayoutInflater.from(AddActivity.this).inflate(R.layout.activity_add,null);
        //窗口动画
        mPopWindow.setAnimationStyle(R.style.contextPhotoAnim);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);

    }


    /**
     * 选择图片结果回调
     */
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
       @Override
       public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
           if(resultList!=null){
               if(reqeustCode == REQUEST_CODE_GALLERY){
                   Log.d(TAG,"打开相册后回调"+resultList.size());


                   PhotoListAdapter adapter = new PhotoListAdapter(AddActivity.this,resultList);
                   mRecyclerView.setAdapter(adapter);

               }else if(reqeustCode == REQUEST_CODE_CAMERA){
                   Log.d(TAG,"打开相机后回调");
               }
           }
       }

       @Override
       public void onHanlderFailure(int requestCode, String errorMsg) {                  //失败时回调
           Toast.makeText(AddActivity.this, "选择图片失败"+errorMsg, Toast.LENGTH_SHORT).show();
           Log.d(TAG,"errorMsg"+errorMsg);
       }
   };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_picture_button:
                //弹出拍照选项
                showPopupWindow();
                break;
            case R.id.pop_photo:
                if(ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //没有权限就申请
                    ActivityCompat.requestPermissions(AddActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    mPopWindow.dismiss();
                    mAddPcitureBtn.setVisibility(View.INVISIBLE);  //隐藏增加的按钮
                }else {
                    //有权限就打开相册
                    Log.d(TAG,"打开了相册");
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, Util.functionConfig, mOnHanlderResultCallback);
                    mPopWindow.dismiss();
                    mAddPcitureBtn.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.pop_photograph:
                //拍照
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA,Util.functionConfig, mOnHanlderResultCallback);
                mPopWindow.dismiss();
                Log.d(TAG,"点击了拍照");
                break;
            case R.id.pop_cancel:
                //取消
                mPopWindow.dismiss();
                break;

        }
    }


}
