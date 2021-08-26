package com.ken.blog.controller.api;

import com.ken.blog.dto.ResponseDto;
import com.ken.blog.model.RoleType;
import com.ken.blog.model.User;
import com.ken.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ // username, password, email
        System.out.println("UserApiController: save execute");
        // insert data into DB
        user.setRole(RoleType.USER);
        int result = userService.signIn(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),result); // convert javascript object (Jackson)
    }
}
