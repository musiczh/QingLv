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
import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.View.iView.RecyclerClickCallback;
import com.example.qinglv.R;
import java.util.List;

/**
 * 路线预览页recyclerView的适配器
 */

public class PathAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Path> mList;
    private Context context;
    private RecyclerClickCallback mClickCallback;

    public PathAdapter(List<Path> list ,RecyclerClickCallback clickCallback ) {
        mClickCallback = clickCallback;
        mList = list;
    }

    //自定义的ViewHolder
    class PathViewHolder extends RecyclerView.ViewHolder {
        View pathView;
        TextView tittleTextView;
        TextView contentTextView;
        ImageView previewImage;
        TextView commentNumTextView;

        PathViewHolder(View itemView) {
            super(itemView);
            pathView = itemView;
            tittleTextView = itemView.findViewById(R.id.textView_item_path_tittle);
            contentTextView = itemView.findViewById(R.id.textView_item_path_introduce);
            previewImage = itemView.findViewById(R.id.imageView_item_path_preview);
            commentNumTextView = itemView.findViewById(R.id.textView_item_path_comment);
        }
    }

    //创建viewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_path, viewGroup, false);
        PathViewHolder pathViewHolder = new PathViewHolder(view);
         final int position = pathViewHolder.getAdapterPosition();
         pathViewHolder.pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.onClick(mList.get(position).getId());
            }
        });
        return pathViewHolder;
    }

    //绑定布局，并传入数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PathViewHolder pathViewHolder = (PathViewHolder) viewHolder;
        Path path = mList.get(i);
        pathViewHolder.commentNumTextView.setText(path.getCommentNum());
        pathViewHolder.contentTextView.setText(path.getIntroduction());
        pathViewHolder.tittleTextView.setText(path.getTitle());
        Glide.with(context).load(path.getPreview()).into(pathViewHolder.previewImage);
    }

    //获得一共有几项
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
