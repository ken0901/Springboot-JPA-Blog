package com.ken.blog.service;

import com.ken.blog.dto.ReplySaveRequestDto;
import com.ken.blog.model.Board;
import com.ken.blog.model.Reply;
import com.ken.blog.model.User;
import com.ken.blog.repository.BoardRepository;
import com.ken.blog.repository.ReplyRepository;
import com.ken.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//@RequiredArgsConstructor
public class BoardService {

      // first way - best way
      // using @RequiredArgsConstructor - final keyword , necessary initialize
//    private final BoardRepository boardRepository;
//    private final UserRepository userRepository;
//    private final ReplyRepository replyRepository;

      // second way
//    private  BoardRepository boardRepository;
//    private  UserRepository userRepository;
//    private  ReplyRepository replyRepository;

//    public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository, UserRepository userRepository){
//        this.replyRepository=replyRepository;
//        this.userRepository=userRepository;
//        this.boardRepository=boardRepository;
//    }

    // third way
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void writing(Board board, User user) { // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    @Transactional(readOnly = true)
    public Page<Board> listOfContents(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board DetailOfContent(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Failed detail of content: not found id");
        });
    }

    @Transactional
    public void delete(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateOfContent(int id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Failed found id: not found id");
        }); // persistence
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // when this method is ended, transaction is also ended then dirty checking starts - auto updated ( DB flush)
    }

    @Transactional
    public void comment(ReplySaveRequestDto replySaveRequestDto) {

        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("Failed add comment: not found id");
        });

        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
            return new IllegalArgumentException("Failed add comment: not found board id ");
        });

        Reply reply = Reply.builder()
                .user(user)
                .board(board)
                .content(replySaveRequestDto.getContent())
                .build();

        // Using native query
//        replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());

        replyRepository.save(reply);
    }

    @Transactional
    public void DeleteComment(int replyId){
        replyRepository.deleteById(replyId);
    }
}
