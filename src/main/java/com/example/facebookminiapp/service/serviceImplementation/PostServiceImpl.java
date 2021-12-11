package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.otherModel.PostDetails;
import com.example.facebookminiapp.model.Comment;
import com.example.facebookminiapp.model.Likes;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repo.CommentRepo;
import com.example.facebookminiapp.repo.LikesRepo;
import com.example.facebookminiapp.repo.UserRepo;
import com.example.facebookminiapp.repo.PostRepo;
import com.example.facebookminiapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    PostRepo postRepo;
    LikesRepo likesRepo;
    CommentRepo commentRepo;
    UserRepo userRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo, LikesRepo likesRepo, CommentRepo commentRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.likesRepo = likesRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    /**
     * CREATE operation on Post
     * @param userId
     * @param post
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean createPost(Long userId, Post post) {
        boolean result = false;
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            postRepo.save(post);
            result = true;
        }
        return result;
    }



    /**
     * GET operation on Post
     * @param postId
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public List<Post> getPostById(Long postId){
        List<Post> postList;
        postList= postRepo.findPostByPostId(postId);
        return postList;
    }

    /**
     * GET by id operation on Post
     * @params postId
     * @return post object
     * */
    public List<PostDetails> getPost(User currentUser) {
        List<PostDetails> posts = new ArrayList<>();
        //get all posts
        List<Post> postData = postRepo.findAllByPostIdIsNotNull();

        for (Post postEach:postData) {

            PostDetails post = new PostDetails();
            post.setId(postEach.getPostId());
            post.setTitle(postEach.getTitle());
            post.setBody(postEach.getBody());
            post.setImageName("image/"+postEach.getImageName());
            post.setName(postEach.getUser().getLastname()+ " "+ postEach.getUser().getFirstname());

            //the total number of likes on this particular post
            List<Likes> numberOfLikes = likesRepo.findAllByPostPostId(postEach.getPostId());
            int likeCount = numberOfLikes.size();
            post.setNoLikes(likeCount);

            //the total number of comments on this particular post
            List<Comment> noOfComment = commentRepo.findAllByPostPostId(postEach.getPostId());
            int commentCount = noOfComment.size();
            post.setNoComments(commentCount);

            //return true if current user liked this post, else false
            List<Likes> postLiked = likesRepo.findAllByPostPostIdAndUserId(postEach.getPostId(), currentUser.getId());
            if(postLiked.size() > 0){
                post.setLikedPost(true);
            }

            posts.add(post);
        }
        Collections.reverse(posts);

        return posts;
    }

    /**
     * CREATE operation on Comment
     * @param postId
     * @param title
     * @param body
     * @return boolean(true for successful update and false on failure on post)
     * */
    public boolean editPost(Long postId, String title, String body) {
        boolean status = false;
        Optional<Post> post = postRepo.findById(postId);
        if(post.isPresent()){
            post.get().setTitle(title);
            post.get().setBody(body);
            postRepo.save(post.get());
            status = true;
        }

        return status;
    }

    /**
     * DELETE operation on Post
     * @param postId
     * @param personId
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean deletePost(Long postId, Long personId){
        boolean status =  false;
        Post post = postRepo.findPostByPostIdAndUserId(postId, personId);
        if(post != null){
            postRepo.deletePostByPostIdAndUserId(postId,personId);
            status = true;
        }
        return status;
    }
}
