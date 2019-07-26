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
import com.example.qinglv.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Food> mList;
    private Context context;

    public FoodAdapter(List<Food> list){
        mList = list;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView tittleTextView;
        TextView contentTextView;
        ImageView previewImage;
        TextView timeTextView;

        FoodViewHolder(View itemView){
            super(itemView);
            tittleTextView = itemView.findViewById(R.id.textView_item_food_tittle);
            contentTextView = itemView.findViewById(R.id.textView_item_food_content);
            previewImage = itemView.findViewById(R.id.imageView_item_food_preview);
            timeTextView = itemView.findViewById(R.id.textView_item_food_time);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_food,viewGroup,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder = (FoodViewHolder) viewHolder;
        Food food = mList.get(i);
        foodViewHolder.timeTextView.setText(food.getDepositTime());
        foodViewHolder.contentTextView.setText(food.getContent());
        foodViewHolder.tittleTextView.setText(food.getTitle());
        Glide.with(context).load(food.getPreview()).into(foodViewHolder.previewImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
