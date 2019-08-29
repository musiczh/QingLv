package com.example.qinglv.MainPackage.View.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.qinglv.MainPackage.Adapter.CommentAdapter;
import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.Presentor.CommentPresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterComment;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewComment;
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerCommentClickCallBack;
import com.example.qinglv.R;
import com.example.qinglv.util.NewRecyclerScrollListener;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static android.view.View.GONE;

public class CommentActivity extends AppCompatActivity implements IViewComment {
    public static final int TRAVEL = 3;
    public static final int PATH = 1;
    public static final int SCENIC = 2;

    private List<Comment> mList = new ArrayList<>();
    private RecyclerViewAdapterWrapper recyclerViewAdapterWrapper;
    private NewRecyclerScrollListener newRecyclerScrollListener;
    private ProgressBar progressBar;
    private IPresenterComment iPresenterComment;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //获取文章id
        Intent intent = getIntent();
        final int articleId = intent.getIntExtra("id",1);
        final int articleType = intent.getIntExtra("articleType",1);

        Toolbar toolbar = findViewById(R.id.toolbar_comment);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final EditText editText = findViewById(R.id.editText_comment);
        iPresenterComment = new CommentPresenter();
        progressBar = findViewById(R.id.progressBar_comment);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //评论列表子项监听
        CommentAdapter commentAdapter = new CommentAdapter(mList, new RecyclerCommentClickCallBack() {
            @Override
            public void clickHead(int UserId) {
                //点击头像监听
            }

            @Override
            public void clickStar(int commentId) {
                //点赞监听
            }

            @Override
            public void clickContent(int commentId, int userId) {
                //点击评论内容监听
            }

            @Override
            public boolean isStar(int commentId) {
                //判断是否点赞
                return false;
            }
        });
        recyclerViewAdapterWrapper = new RecyclerViewAdapterWrapper(commentAdapter);

        //recyclerView的初始化
        recyclerView.setAdapter(recyclerViewAdapterWrapper);
        newRecyclerScrollListener = new NewRecyclerScrollListener() {
            @Override
            public void onLoadMore(int itemCount) {
                iPresenterComment.refreshList(articleId,itemCount,10 , articleType,false);
                recyclerViewAdapterWrapper.setItemState(RecyclerViewAdapterWrapper.LOADING,true);
            }
        };
        recyclerView.addOnScrollListener(newRecyclerScrollListener);

        //输入框的初始化
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                iPresenterComment.postComment(articleId , (v.getText()).toString(),articleType);
                //editText.setText("");
                editText.clearFocus();

                //收起软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) CommentActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) inputMethodManager
                        .hideSoftInputFromWindow(CommentActivity.this.getWindow().getDecorView().getApplicationWindowToken(),
                                0);

                return false;
            }
        });

        //第一次进入刷新的逻辑
        ((CommentPresenter)iPresenterComment).attachView(this);
        iPresenterComment.refreshList(articleId, 0,10,articleType,false);

    }

    //顶部返回按钮的监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    //mvp的View层刷新列表方法
    @Override
    public void setComment(List<Comment> list, boolean isMore , boolean isClear) {
        if (isClear)  mList.clear();
        mList.addAll(list);
        if (isMore) {
            newRecyclerScrollListener.IS_SCROLL = true;
            recyclerViewAdapterWrapper.setItemState(RecyclerViewAdapterWrapper.CONTINUE_DRAG, false);
        }else{
            newRecyclerScrollListener.IS_SCROLL = false;
            recyclerViewAdapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_MORE,false);
        }
        if (progressBar.getVisibility() != GONE){
            progressBar.setVisibility(GONE);
        }

    }

    //mvp接口view层方法展示一个toast
    @Override
    public void setToast(String stringToast) {
        Toast.makeText(this,stringToast,Toast.LENGTH_SHORT).show();
        if (progressBar.getVisibility() != View.GONE){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        InputMethodManager inputMethodManager = (InputMethodManager) CommentActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null)
            if (inputMethodManager.isActive()) inputMethodManager
                    .hideSoftInputFromWindow(view.getApplicationWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        return super.dispatchTouchEvent(ev);
    }
}
