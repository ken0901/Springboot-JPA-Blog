package com.ken.blog.service;

import com.ken.blog.model.Board;
import com.ken.blog.model.User;
import com.ken.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void writing(Board board, User user){ // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    public Page<Board> listOfContents(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board DetailOfContent(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Failed detail of content: not found id");
        });
    }
}
