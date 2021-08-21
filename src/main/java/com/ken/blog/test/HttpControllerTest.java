package com.ken.blog.test;

import org.springframework.web.bind.annotation.*;

// client request -> response(HTML file)
// @Controller

// client request -> response(data)

@RestController
public class HttpControllerTest {

    private static final String TAG= "HttpControllerTest:";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder().username("ken").password("1234").email("ken@gmail.com").build();
//        Member m = new Member(1,"ken","1234","email");
        System.out.println(TAG+"getter: "+m.getUsername());
        m.setUsername("ken1");
        System.out.println(TAG+"setter: "+m.getUsername());
        return "lombok test";
    }

    //http://localhost:8080/http/get (select)
    @GetMapping("http/get")
    public String getTest(Member m){
        return "Request GET : "+m.getId()+", "+m.getUsername()+", "+m.getPassword();
    }
    //http://localhost:8080/http/get (insert)
    @PostMapping("http/post") // text/plain, application/json
    public String postTest(@RequestBody Member m){ // MessageConverter(SpringBoot)
        return "Request POST : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }
    //http://localhost:8080/http/put (update)
    @PutMapping("http/put")
    public String putTest(@RequestBody Member m){
        return "Request PUT : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }
    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("http/delete")
    public String deleteTest(){
        return "Request DELETE";
    }
}
