package com.somnium.inobitesttask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.somnium.inobitesttask.App;
import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.adapters.PostAdapter;
import com.somnium.inobitesttask.api.ObserveOnMainThread;
import com.somnium.inobitesttask.entities.Post;
import com.somnium.inobitesttask.listeners.OnPostClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

import static com.somnium.inobitesttask.Constants.KEY_POST_BODY;
import static com.somnium.inobitesttask.Constants.KEY_POST_ID;
import static com.somnium.inobitesttask.Constants.KEY_POST_TITLE;
import static com.somnium.inobitesttask.GlobalContext.posts;

public class PostActivity extends AppCompatActivity implements OnPostClickListener {

    private RecyclerView postRecyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);
        initUI();
        loadPosts();
    }

    void initUI(){
        postRecyclerView = findViewById(R.id.activity_all_post_list);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postRecyclerView.setHasFixedSize(true);
    }

    private void loadPosts(){
        compositeDisposable.add(App.getPlaceHolderApi().getAllPosts()
                .compose(new ObserveOnMainThread<>())
                .subscribe(this::onGetAllPostSuccess,this::onGetAllPostError));
    }
    private void onGetAllPostSuccess(Response<List<Post>> response) {
        if (response.isSuccessful() && response.body() != null){
            posts = filterElements(response.body());
            postRecyclerView.setAdapter(new PostAdapter(posts,this));
        }
    }

    private void onGetAllPostError(Throwable throwable) {

}

    @Override
    public void onPostClick(Post post) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(KEY_POST_ID,post.getId());
        intent.putExtra(KEY_POST_TITLE,post.getTitle());
        intent.putExtra(KEY_POST_BODY,post.getTitle());
        startActivity(intent);
    }

    private List<Post> filterElements(List<Post> posts){
        List<Post> postsList = new ArrayList<>();
        for (int i=0;i<30;i++){
            postsList.add(posts.get(i));
        }
        return postsList;
    }
}
