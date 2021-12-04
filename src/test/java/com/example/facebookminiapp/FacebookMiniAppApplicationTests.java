package com.example.facebookminiapp;

import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.otherModel.LoginDetails;
import com.example.facebookminiapp.otherModel.PostDetails;
import com.example.facebookminiapp.repository.PostRepository;
import com.example.facebookminiapp.repository.UserRepository;
import com.example.facebookminiapp.service.UserService;
import com.example.facebookminiapp.service.serviceImplementation.PostServiceImpl;
import com.example.facebookminiapp.service.serviceImplementation.UserServiceImpl;
import com.example.facebookminiapp.utils.PasswordHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacebookMiniAppApplicationTests {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    PostServiceImpl postServiceImpl;

    @Autowired
    UserService userService;

    User user;

    Post post;

    PostDetails postDetails;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    LoginDetails loginDetails;


    private String password = "1234567890";
    private String secured = PasswordHashing.encryptPassword(password);

    @Test
    void passwordDecrypt() {
        Assertions.assertEquals(password, PasswordHashing.decryptPassword(secured));
        Assertions.assertNotNull(PasswordHashing.decryptPassword(secured));
    }

    @Test
    void passwordEncrypt(){
        Assertions.assertEquals(secured, PasswordHashing.encryptPassword(password));
        Assertions.assertNotNull(PasswordHashing.encryptPassword(password));
    }

    @Test
    void userCrud() {

        //create
        String password = PasswordHashing.encryptPassword("1234");
        user = new User();
        user.setFirstname("king2");
        user.setLastname("kong3");
        user.setPassword(password);
        user.setEmail("kong6@gmail.com");
        user.setDob("2021-12-25");
        user.setGender("Male");

        userRepository.save(user);


        //Read
        User userData = userRepository.findPersonByEmail("kong6@gmail.com");
        if(!"1234".equals(PasswordHashing.decryptPassword(userData.getPassword()))){
            userData = null;
        }
        assertNotNull(userData);

        assertNotNull(userData.getFirstname());
        assertNotNull(userData.getLastname());
        assertNotNull(userData.getEmail());
        assertNotNull(userData.getPassword());

        assertEquals("king2",userData.getFirstname());
        assertEquals("kong3",userData.getLastname());
        assertEquals("kong6@gmail.com",userData.getEmail());
        assertEquals("1234",PasswordHashing.decryptPassword(user.getPassword()));

        //delete
//        deleteUser(userData.getEmail());
//        assertNull(userData);
    }

//    public void deleteUser(String email){
//        userRepository.deleteUserByEmail(email);
//    }


//    @Test
//    void postCrud() throws SQLException {
//        post = new Post();
//        post.setTitle("test title");
//        post.setBody("test body");
//        post.setImageName("test body");
//
//        user = new User();
//        user.setId(1L);
//
//        //create post
//        boolean success = postServiceImpl.createPost(user.getId(), post);
//        assertTrue(success);
//
//        //get post
//        List<PostDetails> ls = postServiceImpl.getPost(user);
//        assertNotNull(ls);
//
//        //delete post
//        boolean data = postServiceImpl.deletePost(user.getId(), postDetails.getId());
//        assertTrue(data);
//    }

}
