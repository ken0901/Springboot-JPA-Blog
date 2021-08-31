package com.ken.blog.controller.api;

import com.ken.blog.config.auth.PrincipalDetail;
import com.ken.blog.dto.ResponseDto;
import com.ken.blog.model.Board;
import com.ken.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail){
        boardService.writing(board, principalDetail.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // convert javascript object (Jackson)
    }
}
