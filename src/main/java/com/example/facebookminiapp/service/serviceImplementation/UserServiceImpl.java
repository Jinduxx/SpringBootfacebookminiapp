package com.example.facebookminiapp.service.serviceImplementation;

import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.repository.UserRepository;
import com.example.facebookminiapp.service.UserService;
import com.example.facebookminiapp.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * CREATE operation on User
     * @param user
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean createUser(User user){
        boolean flag = false;

        try {
            //password encryption
            user.setPassword(PasswordHashing.encryptPassword(user.getPassword()));

            User userData = userRepository.findPersonByEmail(user.getEmail());


            if(userData == null){
                System.out.println(user);
                userRepository.save(user);
                flag = true;
            } else flag = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  flag;
    }

    /**
     * Get operation on User
     * @param email
     * @param password
     * @return User object
     * */
    public User getUser(String email, String password){

        User userData = null;

        try {

            userData = userRepository.findPersonByEmail(email);

            if(!password.equals(PasswordHashing.decryptPassword(userData.getPassword()))){
                userData = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userData;
    }


}
