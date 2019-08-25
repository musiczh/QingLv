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
import com.example.qinglv.MainPackage.Entity.Comment;
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerCommentClickCallBack;
import com.example.qinglv.R;

import java.util.List;

/**
 * 评论子项recyclerView适配器
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Comment> mList;
    private Context mContext;
    private RecyclerCommentClickCallBack mClickCallback;

    public CommentAdapter(List<Comment> list , RecyclerCommentClickCallBack clickCallback){
        mList = list;
        mClickCallback = clickCallback;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textViewContent;
        ImageView imageViewHeadPortrait;
        ImageView imageViewStar;

        CommentViewHolder(View itemView){
            super(itemView);
            textViewContent = itemView.findViewById(R.id.textView_item_comment_content);
            textViewUserName = itemView.findViewById(R.id.textView_item_comment_userName);
            imageViewHeadPortrait = itemView.findViewById(R.id.imageView_item_comment_head);
            imageViewStar = itemView.findViewById(R.id.imageView_item_comment_star);

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_comment,viewGroup,false);
        final CommentViewHolder commentViewHolder = new CommentViewHolder(view);

        commentViewHolder.imageViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = mList.get(commentViewHolder.getLayoutPosition());
                mClickCallback.clickStar(comment.getId());
            }
        });
        commentViewHolder.imageViewHeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = mList.get(commentViewHolder.getLayoutPosition());
                mClickCallback.clickHead(comment.getUserId());
            }
        });
        commentViewHolder.textViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = mList.get(commentViewHolder.getLayoutPosition());
                mClickCallback.clickContent(comment.getId() , comment.getUserId());
            }
        });
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment comment = mList.get(position);
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.textViewUserName.setText(comment.getNickname());
        commentViewHolder.textViewContent.setText(comment.getContent());
        Glide.with(mContext).load(comment.getHeadPortrait()).into(commentViewHolder.imageViewHeadPortrait);
        if (mClickCallback.isStar(comment.getId())) commentViewHolder.imageViewStar.setImageResource(R.drawable.img_heart_red);
        else commentViewHolder.imageViewStar.setImageResource(R.drawable.img_heart);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
