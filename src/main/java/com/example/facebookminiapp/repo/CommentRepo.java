package com.example.facebookminiapp.repo;

import com.example.facebookminiapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostPostId(Long postId);
    Comment findCommentById(Long commentId);
    @Transactional
    void deleteCommentById(Long id);
}
