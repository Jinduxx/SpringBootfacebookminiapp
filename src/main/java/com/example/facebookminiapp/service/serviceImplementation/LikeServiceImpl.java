package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.model.Likes;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repo.LikesRepo;
import com.example.facebookminiapp.repo.PostRepo;
import com.example.facebookminiapp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private LikesRepo likesRepo;
    private PostRepo postRepo;

    @Autowired
    public LikeServiceImpl(LikesRepo likesRepo, PostRepo postRepo) {
        this.likesRepo = likesRepo;
        this.postRepo = postRepo;
    }

    /**
     * CREATE operation on Comment
     * @param user
     * @param postId
     * @param action
     * @return boolean(true for successful creation and false on failure on liked/unliked post)
     * */
    public boolean likePost(User user, Long postId, String action){
        boolean result = false;

        Optional<Post> post = postRepo.findById(postId);
        if(post.isPresent()){
            Likes like = new Likes();
            like.setUser(user);
            like.setPost(post.get());

            if(action.equals("1")){
                likesRepo.save(like);
                System.out.println("save");
            }else{
                likesRepo.deleteLikesByPostAndUser(post.get(), user);
                System.out.println("delete");
            }
            result = true;
        }

        return result;
    }
}
