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
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.View.iView.RecyclerClickCallback;
import com.example.qinglv.R;
import java.util.List;

/**
 * 风景预览页recyclerView的适配器
 */

public class ScenicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Scenic> mList;
    private Context context;
    private RecyclerClickCallback mClickCallback;

    public ScenicAdapter(List<Scenic> list , RecyclerClickCallback clickCallback){
        mClickCallback = clickCallback;
        mList = list;
    }

    //自定义的ViewHolder
    class ScenicViewHolder extends RecyclerView.ViewHolder{
        View scenicView;
        TextView tittleTextView;
        TextView scoreTextView;
        ImageView previewImage;
        TextView locationTextView;
        TextView commentNumTextView;

        ScenicViewHolder(View itemView){
            super(itemView);
            scenicView = itemView;
            tittleTextView = itemView.findViewById(R.id.textView_item_scenic_tittle);
            scoreTextView = itemView.findViewById(R.id.textView_item_scenic_score);
            previewImage = itemView.findViewById(R.id.imageView_item_scenic_preview);
            commentNumTextView = itemView.findViewById(R.id.textView_item_scenic_comment);
            locationTextView = itemView.findViewById(R.id.textView_item_scenic_location);
        }
    }

    //创建viewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_scenic,viewGroup,false);
        ScenicViewHolder scenicViewHolder = new ScenicViewHolder(view);
        final int position = scenicViewHolder.getAdapterPosition();
        scenicViewHolder.scenicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.onClick(String.valueOf(mList.get(position).getId()) );
            }
        });
        return scenicViewHolder;
    }

    //绑定布局，并传入数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ScenicViewHolder scenicViewHolder = (ScenicViewHolder) viewHolder;
        Scenic scenic = mList.get(i);
        scenicViewHolder.locationTextView.setText(scenic.getLocation());
        scenicViewHolder.commentNumTextView.setText(String.valueOf(scenic.getCommentNum()));
        scenicViewHolder.tittleTextView.setText(scenic.getTitle());
        scenicViewHolder.scoreTextView.setText(String.valueOf( scenic.getScore()));
        Glide.with(context).load(scenic.getPreview()).into(scenicViewHolder.previewImage);
    }

    //获得一共有几项
    @Override
    public int getItemCount() {
        return mList.size();
    }
}