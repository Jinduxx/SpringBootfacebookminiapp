package com.example.facebookminiapp.repo;

import com.example.facebookminiapp.model.Likes;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikesRepo extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPostPostId(Long postId);
    List<Likes> findAllByPostPostIdAndUserId(Long postId, Long personId);
    @Transactional
    void deleteLikesByPostAndUser(Post post, User user);
}
