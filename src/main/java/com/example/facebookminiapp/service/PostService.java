package com.example.facebookminiapp.service;

import com.example.facebookminiapp.otherModel.PostDetails;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;

import java.util.List;

public interface PostService {
    boolean createPost(Long userId, Post post);
    List<PostDetails> getPost(User currentUser);
}
