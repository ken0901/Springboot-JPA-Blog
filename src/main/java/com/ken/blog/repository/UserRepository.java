package com.ken.blog.repository;

import com.ken.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// Auto register to bean
//@Repository  // able to omit
public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}
// JPA naming strategy query
// SELECT * FROM user WHERE username = ?1 AND password = ?2
// User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true )
//    User login(String username, String password);
