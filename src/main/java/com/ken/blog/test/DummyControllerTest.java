package com.ken.blog.test;

import com.ken.blog.model.RoleType;
import com.ken.blog.model.User;
import com.ken.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

// return data NOT HTML file
@RestController
public class DummyControllerTest {

    @Autowired // DI
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ie){
            return "Deleting failed, id doesn't found";
        }

        return "Deleting completed id: "+id;
    }

    // save function - there's no id , only insert data
    // save function - there's id,data exists then update data
    // save function - there's id, data no exist then insert
    // email, password

    @Transactional // function ends, auto commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        // Json data request => Java object(Convert data from MessageConverter's Jackson library )
        System.out.println("id: "+id);
        System.out.println("email: "+requestUser.getEmail());
        System.out.println("password: "+requestUser.getPassword());

        User user = userRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("Updating Failed");
        });
        //dirty checking , persistence
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);


        return user;
    }

    // http://localhost:8000/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list(){
       return userRepository.findAll();
    }

    // 2 data in 1 page
    // paging
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} address with parameter
    // http://localhost:8000/blog/dummy/user/4
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // Ex) user/4 if id is 4 , find 4 in DB but 4 isn't found
        // then it will be null
        // Using Optional to solve this problem


        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("There's no user id: "+id);
            }
        });
        // lambda
//        User user = userRepository.findById(id).orElseThrow(()->{
//                return new IllegalArgumentException("There's no user id: "+id);
//        });
        // request : web browser
        // user object = java object
        // convert ( understandable data for web browser) -> Json (Gson library)
        // Springboot = MessageConverter is auto executed when it's request
        // if java object is returned, MessageConverter calls Jackson library
        // to convert User object to browser
        return user;
    }

    // http://localhost:8000/blog/dummy/join (request)
    // Request with username,password,email in body of http
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());
        System.out.println("role: "+user.getRole());
        System.out.println("createDate: "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "Sign in Complete";
    }
}
