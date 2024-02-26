package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        //로그인한 member session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        //게시글 작성시 작성자 정보 postDTO에 넣기
        if(loggedInMember != null) {
            postDTO.setMember(loggedInMember.toEntity());
        }

        postService.savePost(postDTO);

        return "redirect:/";
    }

    //게시글 상세 페이지로 이동
    @GetMapping("/post/{id}")
    public String getPostDetail(@PathVariable("id") Long postId, Model model){

        //해당 postId 게시글 정보 가져오기
        PostDTO post = postService.getPostDetail(postId);
        model.addAttribute("post", post);

        return "post/postDetail";

    }
}
