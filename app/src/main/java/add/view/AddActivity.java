package add.view;


import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinglv.R;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "AddActivity";

    private ImageButton mAddPcitureBtn;
    private PopupWindow mPopWindow;
    private TextView photoTv;
    private TextView photographTv;
    private TextView cancelTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initalView();
    }


    //初始化控件
    public void initalView(){
        mAddPcitureBtn = findViewById(R.id.add_picture_button);
        mAddPcitureBtn.setOnClickListener(this);
    }

    //弹出拍照窗口
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_picture_button:
                //弹出拍照选项
                showPopupWindow();
                Log.d(TAG,"点击了增加图片");
                break;
            case R.id.pop_photo:
                //相册
                break;
            case R.id.pop_photograph:
                //拍照
                break;
            case R.id.pop_cancel:
                //取消
                mPopWindow.dismiss();
                break;

        }
    }
}
