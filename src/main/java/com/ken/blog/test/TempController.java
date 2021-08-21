package com.ken.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // - return file
public class TempController {

    @GetMapping("/temp/home")
    public String showHTML(){
        // file return default root : src/main/resources/static
        // return name : /home.html
        // full name root : src/main/resources/static/home.html
        System.out.println("tempHome");
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String showJSP(){
        //prefix : /WEB-INF/views/
        //suffix : .jsp
        return "test.jsp";
    }
}
