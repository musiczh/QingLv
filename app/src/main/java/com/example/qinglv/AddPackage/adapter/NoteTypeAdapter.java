package com.example.qinglv.AddPackage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qinglv.AddPackage.entity.NoteType;

import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.List;

public class NoteTypeAdapter extends RecyclerView.Adapter<NoteTypeAdapter.ViewHodel> {

    private List<NoteType> mList;
    private RecyclerClickCallback mClickCallback;
    public NoteTypeAdapter(List<NoteType> list, RecyclerClickCallback clickCallback){
        mList = list;
        mClickCallback = clickCallback;
    }


    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note_type,viewGroup,false);
        ViewHodel viewHodel = new ViewHodel(view);


        return viewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel viewHodel,final int i) {

        NoteType noteType = mList.get(i);
        viewHodel.textView.setText(noteType.getName());

        viewHodel.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.onClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_note_type);
        }
    }

}
