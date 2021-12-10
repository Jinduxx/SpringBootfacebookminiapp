package com.example.facebookminiapp.repo;

import com.example.facebookminiapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findPersonByEmail(String email);
    @Transactional
    User deleteUserByEmail(String email);
}
