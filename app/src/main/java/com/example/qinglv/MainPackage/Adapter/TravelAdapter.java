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
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.List;

import static com.example.qinglv.util.StaticQuality.PREFIX_IMAGE;

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
        final TravelViewHolder travelViewHolder = new TravelViewHolder(view);
        travelViewHolder.travelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = travelViewHolder.getLayoutPosition();
                mClickCallBack.onClick(position);
            }
        });
        return travelViewHolder;
    }

    //绑定布局，并传入数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TravelViewHolder travelViewHolder = (TravelViewHolder) viewHolder;

        Travel travel = mList.get(i);
        if (travel.getNickName()!=null) travelViewHolder.userNameTextView.setText(travel.getNickName());
        if (travel.getStarNum()!=null) travelViewHolder.HeartTextView.setText(travel.getStarNum());
        travelViewHolder.tittleTextView.setText(travel.getTitle());
        List<String> strings = travel.getPhoto();
        String url;
        String s = null;
        if (strings!=null) {
            s = strings.get(0);
            Glide.with(context).load(s)
                    .placeholder(R.drawable.gif)
                    .error(R.drawable.img_no_img)
                    //.override(120,20)
                    .into(travelViewHolder.previewImage);
        }else{
            Glide.with(context).load(R.drawable.img_no_img).into(travelViewHolder.previewImage);
        }
        if (travel.getHeadPortrait().length()<10) {
            url = PREFIX_IMAGE+travel.getHeadPortrait();
        }else{
            url = travel.getHeadPortrait();
        }
        Glide.with(context).load(url).into(travelViewHolder.userHeadImage);
    }

    //获得一共有几项
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
