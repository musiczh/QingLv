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
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.R;

import java.util.List;

/**
 * 美食预览页recyclerView的适配器
 */

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Food> mList;
    private Context context;
    private RecyclerClickCallback mClickCallback;

    public FoodAdapter(List<Food> list , RecyclerClickCallback clickCallback){
        mClickCallback = clickCallback;
        mList = list;
    }

    //自定义的ViewHolder
    class FoodViewHolder extends RecyclerView.ViewHolder{
        View foodView;
        TextView tittleTextView;
        TextView contentTextView;
        ImageView previewImage;
        TextView timeTextView;

        FoodViewHolder(View itemView){
            super(itemView);
            foodView = itemView;
            tittleTextView = itemView.findViewById(R.id.textView_item_food_tittle);
            contentTextView = itemView.findViewById(R.id.textView_item_food_content);
            previewImage = itemView.findViewById(R.id.imageView_item_food_preview);
            timeTextView = itemView.findViewById(R.id.textView_item_food_time);
        }
    }

    //创建viewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_food,viewGroup,false);
        final FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        foodViewHolder.foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = foodViewHolder.getLayoutPosition();
                mClickCallback.onClick(position);
            }
        });

        return foodViewHolder;
    }

    //绑定布局，并传入数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder = (FoodViewHolder) viewHolder;



        //绑定数据
        Food food = mList.get(i);
        String content = food.getContent().substring(0,27)+"......点击查看详情";
        foodViewHolder.timeTextView.setText(food.getDepositTime());
        foodViewHolder.contentTextView.setText(content);
        foodViewHolder.tittleTextView.setText(food.getTitle());
        Glide.with(context).load(food.getPreview()).into(foodViewHolder.previewImage);
    }

    //获得一共有几项
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
