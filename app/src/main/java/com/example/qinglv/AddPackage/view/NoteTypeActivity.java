package com.example.qinglv.AddPackage.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.qinglv.AddPackage.adapter.NoteTypeAdapter;
import com.example.qinglv.AddPackage.contract.NoteTypeContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.presenter.NoteTypePresenter;
import com.example.qinglv.R;

import java.util.List;

public class NoteTypeActivity extends AppCompatActivity implements NoteTypeContract.IView {

    private RecyclerView mRecyclerView;
    private NoteTypeContract.IPresenter mPresenter;
    private List<NoteType> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_type);
        initView();
    }

    public void initView(){
        mRecyclerView = findViewById(R.id.note_type_recyView);
        mPresenter = new NoteTypePresenter();

    }

    @Override
    public void setList(List list) {
        NoteTypeAdapter adapter = new NoteTypeAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setErrorToast(String string) {

    }
}
