package com.example.facebookminiapp.service;

import com.example.facebookminiapp.otherModel.CommentDetails;
import com.example.facebookminiapp.model.Comment;
import com.example.facebookminiapp.model.User;

import java.util.List;

public interface CommentService {
    boolean createComment(Long userId, Long postId, Comment comment);
    List<CommentDetails> getComments(Long postId);
    boolean editComment(Long commentId, User user, Long postId, String comment);
    boolean deleteComment(Long commentId);
}
