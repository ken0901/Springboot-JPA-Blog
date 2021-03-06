package com.ken.blog.controller.api;

import com.ken.blog.dto.ResponseDto;
import com.ken.blog.model.User;
import com.ken.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @Autowired
//    private HttpSession session;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
//        System.out.println("UserApiController: save execute");
        // insert data into DB
        userService.signIn(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // convert javascript object (Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { // if no using RequestBody using key=value, x-www-form-urlencoded
        userService.update(user);
        // transation ends here so DB value is changed
        // but session doesn't change yet we should change session value
        // session registers

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
/*
old log in way
@PostMapping("/api/user/login")
public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
    System.out.println("UserApiController: login execute");
    User principal = userService.login(user);

    if(principal!=null){
        session.setAttribute("principal",principal);
    }
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
}*/
