package com.ken.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

    @Test
    public void hash_bcrypt(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 hash: "+encPassword);
    }
}
