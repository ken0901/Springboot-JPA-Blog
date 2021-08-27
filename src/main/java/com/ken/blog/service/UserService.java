package com.ken.blog.service;

import com.ken.blog.model.User;
import com.ken.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// Regsister bean to IOC through Spring component scan
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int signIn(User user){
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("UsrService: SignIn(): "+e.getMessage());
        }
        return -1;
    }

    @Transactional(readOnly = true) // when it's selected transaction starts, service ends transaction also ends
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
