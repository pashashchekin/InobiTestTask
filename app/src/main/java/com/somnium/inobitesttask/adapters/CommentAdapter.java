package com.somnium.inobitesttask.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.adapters.viewholders.CommentViewHolder;
import com.somnium.inobitesttask.entities.CommentPost;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<CommentPost> commentPosts;

    public CommentAdapter(List<CommentPost> commentPosts) {
        this.commentPosts = commentPosts;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.bind(commentPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return commentPosts.size();
    }
}
