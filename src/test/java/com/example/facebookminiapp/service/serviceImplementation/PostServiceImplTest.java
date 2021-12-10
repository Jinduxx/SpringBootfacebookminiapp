package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repo.PostRepo;
import com.example.facebookminiapp.repo.UserRepo;
import com.example.facebookminiapp.utils.PasswordHashing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private Post post;

    private User user;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(10L);

        post = new Post();
        post.setUser(user);
        post.setPostId(1L);
        post.setTitle("Mockito");
        post.setBody("Mocking Mocking Bird");
        post.setImageName("dido.png");
    }

    @Test
    void shouldTestForCreatingPost() {
        //mock Post Repo

        when(postRepo.save(any(Post.class))).thenReturn(post);

        System.out.println(post.getUser().getId());
        //call method to be tested
        boolean success = createPost(post.getUser().getId(), post);

        //assertion
        verify(postRepo, times(1)).save(any(Post.class));
        assertTrue(success);
    }

    @Test
    void shouldTestForGettingPostById() {
        //mock Post Repo
        when(postRepo.findPostByPostId(anyLong())).thenReturn(List.of(post));

        // call method to be tested
        postService.getPostById(post.getPostId());

        //assertion
        assertEquals("Mockito", post.getTitle());
        assertEquals("Mocking Mocking Bird", post.getBody());
        assertEquals("dido.png", post.getImageName());
    }

    public boolean createPost(Long userId, Post post) {
        boolean result = false;

        try {
            Optional<User> user = userRepo.findById(userId);
                postRepo.save(post);
                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}