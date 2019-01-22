package com.somnium.inobitesttask.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.adapters.viewholders.PostItemViewHolder;
import com.somnium.inobitesttask.entities.Post;
import com.somnium.inobitesttask.listeners.OnItemClickListener;
import com.somnium.inobitesttask.listeners.OnPostClickListener;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostItemViewHolder> implements  OnItemClickListener{

    private List<Post> posts;
    private OnPostClickListener listener;

    public PostAdapter(List<Post> posts,OnPostClickListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @Override
    public PostItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostItemViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(PostItemViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    @Override
    public void onItemClick(int position) {
        if(position != RecyclerView.NO_POSITION) {
            listener.onPostClick(posts.get(position));
        }
    }
}
