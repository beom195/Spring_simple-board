package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체 게시글 불러오기
    @GetMapping("/")
    public String viewAllPosts(Model model){
        List<PostDTO> posts = postService.viewAllPosts();
        model.addAttribute("posts", posts);
        return "index.html";

    }
}
