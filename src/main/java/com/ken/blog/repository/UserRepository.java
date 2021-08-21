package com.ken.blog.repository;

import com.ken.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// Auto register to bean
//@Repository  // possible omit
public interface UserRepository extends JpaRepository<User,Integer> {

}
