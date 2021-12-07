package com.example.facebookminiapp.service;

import com.example.facebookminiapp.model.User;

public interface UserService {
    boolean createUser(User user);
    User getUser(String email, String password);
}
