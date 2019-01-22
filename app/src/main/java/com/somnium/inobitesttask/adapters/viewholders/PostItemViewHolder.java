package com.somnium.inobitesttask.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.entities.Post;
import com.somnium.inobitesttask.listeners.OnItemClickListener;

public class PostItemViewHolder extends RecyclerView.ViewHolder {
    private  TextView postId;
    private  TextView postTitle;

    public PostItemViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        init();
        itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
    }

    private void init() {
        postId = itemView.findViewById(R.id.post_id);
        postTitle = itemView.findViewById(R.id.post_title);
    }

    public void bind(Post post){
        postId.setText(Integer.toString(post.getId())+")");
        postTitle.setText(post.getTitle());
    }



}
