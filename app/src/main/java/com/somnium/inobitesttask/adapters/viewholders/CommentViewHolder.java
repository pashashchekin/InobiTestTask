package com.somnium.inobitesttask.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.entities.CommentPost;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    private TextView commentName;
    private TextView commentEmail;
    private TextView commentBody;

    public CommentViewHolder(View itemView) {
        super(itemView);
        init();
    }

    private void init() {
        commentName = itemView.findViewById(R.id.comment_name);
        commentEmail = itemView.findViewById(R.id.comment_email);
        commentBody = itemView.findViewById(R.id.comment_txt);
    }

    public void bind(CommentPost commentPost){
        commentName.setText(commentPost.getName());
        commentEmail.setText(commentPost.getEmail());
        commentBody.setText(commentPost.getBody());
    }
}
