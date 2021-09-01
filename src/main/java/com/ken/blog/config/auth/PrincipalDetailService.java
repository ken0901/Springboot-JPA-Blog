package com.ken.blog.config.auth;

import com.ken.blog.model.User;
import com.ken.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //bean
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // spring security will intercept username, password when it's login
    // password will be processed from security
    // manually check username is in DB
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUser method execute");
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("NO found user name" + username);
                });
        System.out.println("end: " + username);
        return new PrincipalDetail(principal); // user info is saved to security session
    }
}
