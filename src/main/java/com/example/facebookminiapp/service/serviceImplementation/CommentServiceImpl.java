package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.otherModel.CommentDetails;
import com.example.facebookminiapp.model.Comment;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repository.CommentRepository;
import com.example.facebookminiapp.repository.PostRepository;
import com.example.facebookminiapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    /**
      * CREATE operation on Comment
      * @param comment
      * @param postId
      * @param userId
      * @return boolean(true for successful creation and false on failure on create)
      * */
    public boolean createComment(Long userId, Long postId, Comment comment){
        boolean result = false;

        try{
            Post post = postRepository.findById(postId).get();
            //set the post
            comment.setPost(post);

            commentRepository.save(comment);
            result = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * GET operation on Comment
     * @param postId
     * @return List of comments
     * */
    public List<CommentDetails> getComments(Long postId){
        List<CommentDetails> comments = new ArrayList();

        try{

            List<Comment> commentsData = commentRepository.findAllByPostPostId(postId);

            for (Comment commentEach:commentsData) {
                CommentDetails comment = new CommentDetails();
                comment.setId(commentEach.getId());
                comment.setPostId(commentEach.getPost().getPostId());
                comment.setComment(commentEach.getComment());
                comment.setUsername(commentEach.getUser().getLastname()+" "+commentEach.getUser().getFirstname());
                comment.setTitle(commentEach.getPost().getTitle());
                comment.setImageName("/image/"+commentEach.getPost().getImageName());
                comment.setUserId(commentEach.getUser().getId());

                comments.add(comment);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;
    }

    /**
     * CREATE operation on Comment
     * @param comment
     * @param postId
     * @param commentId
     * @param user
     * @return boolean(true for successful creation and false on failure on comment update)
     * */
    public boolean editComment(Long commentId, User user, Long postId, String comment) {
        boolean status = false;

        try {
            Post post = postRepository.findById(postId).get();

            Comment data = commentRepository.findCommentById(commentId);

            data.setComment(comment);
            data.setUser(user);
            data.setPost(post);
            commentRepository.save(data);

            status = true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * DELETE operation on Comment
     * @param commentId
     * @return boolean(true for successful deletion and false on failure to delete)
     * */
    public boolean deleteComment(Long commentId){
        boolean status =  false;

        try {
            commentRepository.deleteCommentById(commentId);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
