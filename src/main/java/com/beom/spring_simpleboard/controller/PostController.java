package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 목록으로 돌아가기
    @GetMapping("/post/viewPosts")
    public String getPosts(){
        return "redirect:/";
    }


    //전체 게시글 불러오기
    @GetMapping("/")
    public String viewAllPosts(Model model, @PageableDefault(sort = "postId", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Post> postList = postService.postList(pageable);
        model.addAttribute("nowPage", postList.getNumber() + 1); // 현재 페이지 번호
        model.addAttribute("startPage", Math.max(postList.getNumber() - 4, 1)); // 시작 페이지 번호
        model.addAttribute("endPage", Math.min(postList.getNumber() + 5, postList.getTotalPages())); // 끝 페이지 번호
        model.addAttribute("posts", postList); // 전체 게시글 목록
        log.info("posts = {}", postList);
        return "index";
    }

    //게시글 작성
    @PostMapping("/post/write")
    public String writePost(PostDTO postDTO, HttpSession session) {

        //로그인한 member session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        //게시글 작성시 작성자 정보 postDTO에 넣기
        if (loggedInMember != null) {
            postDTO.setMember(loggedInMember.toEntity());
        }

        postService.savePost(postDTO);

        return "redirect:/";
    }

    //게시글 수정 페이지로 이동
    @GetMapping("/post/edit/{id}")
    public String getPostUpdate(@PathVariable("id") Long postId, Model model) {
        log.info("수정 페이지로 이동");
        PostDTO post = postService.getPostDetail(postId);
        model.addAttribute("post", post);
        return "post/postEdit";
    }

    //게시글 수정하기
    @PostMapping("/post/edit/{id}")
    public String postUpdate(@PathVariable("id") Long postId, PostDTO postDTO) {
        log.info("게시글 수정하기");
        postService.postUpdate(postId, postDTO);
        return "redirect:/post/detail/{id}";
    }

    //게시글 삭제하기
    @DeleteMapping("/post/delete/{id}")
    public String postDelete(@PathVariable("id") Long postId) {
        log.info("게시글 삭제하기");
        postService.postDelete(postId);
        return "redirect:/";
    }


}
