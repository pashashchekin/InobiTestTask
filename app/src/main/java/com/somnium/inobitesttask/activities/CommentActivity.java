package com.somnium.inobitesttask.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.somnium.inobitesttask.App;
import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.adapters.CommentAdapter;
import com.somnium.inobitesttask.api.ObserveOnMainThread;
import com.somnium.inobitesttask.dialogs.AddCommentDialog;
import com.somnium.inobitesttask.entities.CommentPost;
import com.somnium.inobitesttask.listeners.AddCommentDialogListener;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

import static com.somnium.inobitesttask.Constants.KEY_POST_BODY;
import static com.somnium.inobitesttask.Constants.KEY_POST_ID;
import static com.somnium.inobitesttask.Constants.KEY_POST_TITLE;
import static com.somnium.inobitesttask.GlobalContext.commentPosts;
import static com.somnium.inobitesttask.GlobalContext.posts;

public class CommentActivity extends Activity {

    private int postId;
    private TextView postTitle;
    private TextView postBody;
    private Button addCommentButton;


    private RecyclerView commentRecyclerview;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStart() {
        super.onStart();
        initUI();
        initData();
        loadComments();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity);
    }

    private void initUI() {
        postTitle = findViewById(R.id.post_title_for_comment);
        postBody = findViewById(R.id.post_body_for_comment);

        addCommentButton = findViewById(R.id.show_add_comment_dialog);
        addCommentButton.setOnClickListener(view -> addComment());

        commentRecyclerview = findViewById(R.id.all_comments_in_post);
        commentRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerview.setHasFixedSize(true);
    }

    private void initData(){
        postId = getIntent().getIntExtra(KEY_POST_ID,0);
        postTitle.setText(getIntent().getStringExtra(KEY_POST_TITLE));
        postBody.setText(getIntent().getStringExtra(KEY_POST_BODY));
    }


    private void addComment() {
        AddCommentDialog addCommentDialog = new AddCommentDialog(this, new AddCommentDialogListener() {
            @Override
            public void onClickAddComment(String name, String email, String text) {
                commentPosts.add(new CommentPost(name,email,text));
                commentRecyclerview.setAdapter(new CommentAdapter(commentPosts));
                Toast.makeText(getApplicationContext(),R.string.comment_added, Toast.LENGTH_SHORT).show();
            }
        });
        addCommentDialog.show();
    }


    private void loadComments(){
        compositeDisposable.add(App.getPlaceHolderApi().getAllComments(postId)
                .compose(new ObserveOnMainThread<>())
                .subscribe(this::onGetAllCommentsSuccess,this::onGetAllCommentsError));
    }


    private void onGetAllCommentsSuccess(Response<List<CommentPost>> response) {
        if (response.isSuccessful() && response.body() != null){
            commentPosts.addAll(response.body());
            commentRecyclerview.setAdapter(new CommentAdapter(commentPosts));
        }
    }



    private void onGetAllCommentsError(Throwable throwable) {
    }
}
