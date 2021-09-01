package com.ken.blog.service;

import com.ken.blog.model.Board;
import com.ken.blog.model.User;
import com.ken.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional(readOnly = true)
    public Page<Board> listOfContents(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board DetailOfContent(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Failed detail of content: not found id");
        });
    }

    @Transactional
    public void delete(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateOfContent(int id, Board requestBoard){
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Failed found id: not found id");
        }); // persistence
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // when this method is ended, transaction is also ended then dirty checking starts - auto updated ( DB flush)
    }
}
