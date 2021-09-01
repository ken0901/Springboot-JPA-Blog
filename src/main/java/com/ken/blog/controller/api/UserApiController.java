package com.ken.blog.controller.api;

import com.ken.blog.dto.ResponseDto;
import com.ken.blog.model.User;
import com.ken.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


//    @Autowired
//    private HttpSession session;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
        System.out.println("UserApiController: save execute");
        // insert data into DB
        userService.signIn(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // convert javascript object (Jackson)
    }

    /*
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController: login execute");
        User principal = userService.login(user);

        if(principal!=null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }*/
}
