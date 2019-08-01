package com.example.qinglv.MainPackage.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.View.fragment.FragmentShareTravels;
import com.example.qinglv.MainPackage.View.iView.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 游记预览页recyclerView的适配器
 */

public class TravelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Travel> mList;
    private Context context;
    private RecyclerClickCallback mClickCallBack;

    public TravelAdapter(List<Travel> list , RecyclerClickCallback clickCallBack){
        mList = list;
        mClickCallBack = clickCallBack;
    }

    //自定义的ViewHolder
    class TravelViewHolder extends RecyclerView.ViewHolder{
        View travelView;
        TextView tittleTextView;
        TextView HeartTextView;
        ImageView previewImage;
        TextView userNameTextView;
        ImageView userHeadImage;

        TravelViewHolder(View itemView){
            super(itemView);
            travelView = itemView;
            tittleTextView = itemView.findViewById(R.id.textView_item_travel_tittle);
            HeartTextView = itemView.findViewById(R.id.textView_item_travel_heart);
            previewImage = itemView.findViewById(R.id.imageView_item_travel_preview);
            userNameTextView = itemView.findViewById(R.id.textView_item_travel_userName);
            userHeadImage = itemView.findViewById(R.id.imageView_travel_userHead);
        }
    }
//
    //创建viewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_travel,viewGroup,false);
        return new TravelViewHolder(view);
    }

    //绑定布局，并传入数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TravelViewHolder travelViewHolder = (TravelViewHolder) viewHolder;
        final int position = i;
        travelViewHolder.travelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallBack.onClick(position);
            }
        });
        Travel travel = mList.get(i);
        travelViewHolder.userNameTextView.setText(travel.getNickName());
        travelViewHolder.HeartTextView.setText(travel.getStarNum());
        travelViewHolder.tittleTextView.setText(travel.getTitle());
        List<String> strings = travel.getPhoto();
        String s;
        if (strings!=null) { s= strings.get(0);}
        else s="123";
        Glide.with(context).load(travel.getHeadPortrait()).into(travelViewHolder.userHeadImage);
        Glide.with(context).load(s).into(travelViewHolder.previewImage);
    }

    //获得一共有几项
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
