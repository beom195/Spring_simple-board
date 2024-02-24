package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        log.info("postst = {}", posts);
        return "index";
    }

    //게시글 작성
    @PostMapping("/post/write")
    public String writePost(PostDTO postDTO, HttpSession session){

        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        if (loggedInMember != null) {
            postDTO.setMember(loggedInMember.toEntity());
        }

        postService.savePost(postDTO);

        return "redirect:/";
    }
}
