package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repo.UserRepo;
import com.example.facebookminiapp.utils.PasswordHashing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private String passCode = "1234";
    private String encodedPass = PasswordHashing.encryptPassword(passCode);
    private String decodedPass = PasswordHashing.decryptPassword(encodedPass);

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(10L);
        user.setFirstname("King");
        user.setLastname("Kong");
        user.setEmail("king@gmail.com");
        user.setPassword(encodedPass);
        user.setDob("12-04-1990");
        user.setGender("Male");

    }

    @Test
    void shouldTestForCreationOfUser() {
        //mock UserRepo

        when(userRepo.save(any(User.class))).thenReturn(user);

        //call method to be tested

        userService.createUser(user);

        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void shouldTestForGettingUser() {
        //mock user Repo

        when(userRepo.findPersonByEmail(user.getEmail())).thenReturn(user);

        // call method to be tested

        User userDetail = userService.getUser(user.getEmail(), decodedPass);

        //assertions
        assertNotNull(userDetail);
        assertEquals("king@gmail.com", userDetail.getEmail());
        assertEquals("King", userDetail.getFirstname());
        assertEquals("Kong", userDetail.getLastname());
        assertEquals("Male", userDetail.getGender());
        assertEquals("1234", PasswordHashing.decryptPassword(userDetail.getPassword()));

        verify(userRepo, times(1)).findPersonByEmail(user.getEmail());

    }

//    @Test
//    void shouldTestForDeletingOfUser() {
//        //mock User Repo
//
//        when(userRepo.deleteUserByEmail(user.getEmail())).thenReturn(null);
//
//        // call method to be tested
//
//        userService.deleteUser(user.getEmail());
//
//        //assertions
//
//        assertNull(user);
//
////        assertTrue(success);
//    }
}