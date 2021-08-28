package com.ken.blog.controller;

import com.ken.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

//    @Autowired
//    private PrincipalDetail principalDetail;

    // @AuthenticationPrincipal PrincipalDetail principalDetail
    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        // /WEB-INF/views/index.jsp
        System.out.println("log in id: "+principalDetail.getUsername());
        return "index";
    }
}
