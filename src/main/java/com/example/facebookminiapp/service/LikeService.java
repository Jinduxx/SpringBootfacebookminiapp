package com.example.facebookminiapp.service;

import com.example.facebookminiapp.model.User;

public interface LikeService {
    boolean likePost(User user, Long postId, String action);
}
