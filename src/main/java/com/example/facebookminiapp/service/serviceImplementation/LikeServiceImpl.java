package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.model.Likes;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repository.LikesRepository;
import com.example.facebookminiapp.repository.PostRepository;
import com.example.facebookminiapp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;

    /**
     * CREATE operation on Comment
     * @param user
     * @param postId
     * @param action
     * @return boolean(true for successful creation and false on failure on liked/unliked post)
     * */
    public boolean likePost(User user, Long postId, String action){
        boolean result = false;

        Post post = postRepository.findById(postId).get();

        try{
            Likes like = new Likes();
            like.setUser(user);
            like.setPost(post);

            if(action.equals("1")){
                likesRepository.save(like);
                System.out.println("save");
            }else{
                likesRepository.deleteLikesByPostAndUser(post, user);
                System.out.println("delete");
            }

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
