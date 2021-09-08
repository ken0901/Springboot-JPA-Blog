package com.ken.blog.test;

import com.ken.blog.model.Reply;
import org.junit.jupiter.api.Test;

public class ReplyObjectTest {

    @Test
    public void toStringTest(){
        Reply reply = Reply.builder()
                .id(1)
                .user(null)
                .board(null)
                .content("hi")
                .build();
        System.out.println(reply);
    }
}
