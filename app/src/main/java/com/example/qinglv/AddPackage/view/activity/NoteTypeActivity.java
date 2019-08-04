package com.example.qinglv.AddPackage.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.qinglv.AddPackage.presenter.NoteTypeBasePresenter;
import com.example.qinglv.AddPackage.adapter.NoteTypeAdapter;
import com.example.qinglv.AddPackage.contract.INoteTypeContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.presenter.NoteTypePresenter;
import com.example.qinglv.MainPackage.View.iView.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 选择游记种类的activity，使用了mvp模式
 */

public class NoteTypeActivity extends AppCompatActivity implements INoteTypeContract.IView<NoteType> {

    private RecyclerView mRecyclerView;
    private INoteTypeContract.IPresenter mPresenter;
    private List<NoteType> mList = new ArrayList<>();

    private NoteTypeBasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_type);
        initView();
    }

    public void initView(){
        mRecyclerView = findViewById(R.id.note_type_recyView);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        presenter = new NoteTypePresenter();
        ((NoteTypePresenter) presenter).getDatas();
        presenter.attachView(this);

    }


    @Override
    public void setList(final List<NoteType> list) {
        Log.d("mRecyclerView","是否为空"+mRecyclerView);
     NoteTypeAdapter adapter = new NoteTypeAdapter(list, new RecyclerClickCallback() {
         @Override
         public void onClick(int position) {
             Log.d("NoteTypeAdapter","点击了"+position);
             Intent intent = new Intent();
             intent.putExtra("noteType",list.get(position).getName());
             intent.putExtra("tabId",list.get(position).getId());
             setResult(0,intent);                 //销毁时返回数据
             finish();
         }

         @Override
         public void onLongClick() {

         }
     });
     mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setErrorToast(String string) {
        Toast.makeText(NoteTypeActivity.this,string,Toast.LENGTH_SHORT).show();
    }
}
