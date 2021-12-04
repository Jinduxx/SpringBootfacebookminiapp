package com.example.facebookminiapp.repository;

import com.example.facebookminiapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByPostId(Long postId);
    @Transactional
    void deletePostByPostIdAndUserId(Long postId, Long personId);
    Post findPostByPostIdAndUserId(Long postId, Long personId);
    List<Post> findAllByCheckerIsOrderByPostIdDesc(String checker);
}
