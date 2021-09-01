package com.ken.blog.service;

import com.ken.blog.model.RoleType;
import com.ken.blog.model.User;
import com.ken.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// Regsister bean to IOC through Spring component scan
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional
    public void signIn(User user) {
        String rawPassword = user.getPassword(); //1234 original
        String encPassword = encode.encode(rawPassword); // hash
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);

    }

    /*@Transactional(readOnly = true) // when it's selected transaction starts, service ends transaction also ends
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }*/
}
