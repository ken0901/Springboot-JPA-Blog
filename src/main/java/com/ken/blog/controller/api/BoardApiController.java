package com.ken.blog.controller.api;

import com.ken.blog.config.auth.PrincipalDetail;
import com.ken.blog.dto.ReplySaveRequestDto;
import com.ken.blog.dto.ResponseDto;
import com.ken.blog.model.Board;
import com.ken.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.writing(board, principalDetail.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updateOfContent(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // receive data in controller, creating dto layer is better for the program
    // doesn't use dto layer because it's small project
    @PostMapping("/api/board/{boardId}/reply")
    // @PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        boardService.comment(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        System.out.println("reply Delete execute");
        boardService.DeleteComment(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
