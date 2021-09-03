package com.ken.blog.service;

import com.ken.blog.model.RoleType;
import com.ken.blog.model.User;
import com.ken.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Register bean to IOC through Spring component scan
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }

    @Transactional
    public void signIn(User user) {
        String rawPassword = user.getPassword(); //1234 original
        String encPassword = encode.encode(rawPassword); // hash
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        // when it's updating, persist User object from persistence context and then update persisted User object
        // Bring User object (using select query ) from the DB because of the persistence
        // Updating persisted Object is auto-sending update query to DB
        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("No found user");
        });

        // Validate check -> enable updating when oauth field is empty ( already user)
        if(persistence.getOauth()==null || persistence.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = encode.encode(rawPassword);
            persistence.setPassword(encPassword);
            persistence.setEmail(user.getEmail());
        }
        // updating user ends = service ends = transaction ends = auto commit
        // persistence object is changed, dirty checking starts (sending update query)
    }

    /*@Transactional(readOnly = true) // when it's selected transaction starts, service ends transaction also ends
    public User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }*/
}
