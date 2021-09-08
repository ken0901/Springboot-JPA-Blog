package com.ken.blog.test;

import com.ken.blog.model.Board;
import com.ken.blog.model.Reply;
import com.ken.blog.repository.BoardRepository;
import com.ken.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id){
        return boardRepository.findById(id).get(); // jackson library(return object type) => call getter from model layer
    }

    @GetMapping("/test/reply")
    public List<Reply> getReply(){
        return replyRepository.findAll(); // jackson library(return object type) => call getter from model layer
    }
}
