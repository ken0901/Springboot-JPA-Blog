package com.ken.blog.controller;

import com.ken.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

//    @Autowired
//    private PrincipalDetail principalDetail;

    @Autowired
    private BoardService boardService;

    // @AuthenticationPrincipal PrincipalDetail principalDetail
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        // /WEB-INF/views/index.jsp
        model.addAttribute("boards",boardService.listOfContents(pageable));
        return "index"; // execute viewResolver
    }

    // for USER role, need sign in
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String findByID(@PathVariable int id,Model model){
        model.addAttribute(boardService.DetailOfContent(id));
        return "board/detail";
    }

    @GetMapping("board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board",boardService.DetailOfContent(id));
        return "board/updateForm";
    }
}
