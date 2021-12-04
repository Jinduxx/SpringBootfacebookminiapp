package com.example.facebookminiapp.repository;

import com.example.facebookminiapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findPersonByEmail(String email);
    User deleteUserByEmail(String email);
}
