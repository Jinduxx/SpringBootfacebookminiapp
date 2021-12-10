package com.example.facebookminiapp;

import com.example.facebookminiapp.utils.PasswordHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacebookMiniAppApplicationTests {

    private String password = "1234567890";
    private String secured = PasswordHashing.encryptPassword(password);

    @Test
    void passwordDecrypt() {
        assertEquals(password, PasswordHashing.decryptPassword(secured));
        assertNotNull(PasswordHashing.decryptPassword(secured));
    }

    @Test
    void passwordEncrypt(){
        assertEquals(secured, PasswordHashing.encryptPassword(password));
        assertNotNull(PasswordHashing.encryptPassword(password));
    }

}
