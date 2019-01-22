package com.somnium.inobitesttask;

import com.somnium.inobitesttask.entities.CommentPost;
import com.somnium.inobitesttask.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class GlobalContext {
    public static List<Post> posts = new ArrayList<>();
    public static List<CommentPost> commentPosts = new ArrayList<>();
}
