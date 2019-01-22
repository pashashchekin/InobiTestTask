package com.somnium.inobitesttask.api;

import com.somnium.inobitesttask.entities.CommentPost;
import com.somnium.inobitesttask.entities.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceHolderApi {
    @GET("/posts")
    Observable<Response<List<Post>>> getAllPosts();

    @GET("/comments")
    Observable<Response<List<CommentPost>>> getAllComments(@Query("postId") int postId);
}
