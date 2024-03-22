package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.CommentDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.CommentService;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    //게시글 목록으로 돌아가기
    @GetMapping("/post/viewPosts")
    public String getPosts(){
        return "redirect:/";
    }


    //전체 게시글 불러오기
    @GetMapping("/")
    public String viewAllPosts(Model model, @PageableDefault(sort = "postId", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<PostDTO> postList = postService.postList(pageable);
        model.addAttribute("nowPage", postList.getNumber() + 1); // 현재 페이지 번호
        model.addAttribute("startPage", Math.max(postList.getNumber() - 4, 1)); // 시작 페이지 번호
        model.addAttribute("endPage", Math.min(postList.getNumber() + 5, postList.getTotalPages())); // 끝 페이지 번호
        model.addAttribute("posts", postList); // 전체 게시글 목록
        log.info("posts = {}", postList);
        return "index";
    }

    //게시글 상세 페이지로 이동
    @GetMapping("/post/detail/{id}")
    public String getPostDetail(@PathVariable("id") Long postId, Model model,
                                HttpServletRequest request, HttpServletResponse response, HttpSession session){

        //session에 저장된 member 정보가 일치하면 수정과 삭제 버튼을 보이게하기 위해 member session 받아오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        //session에 저장된 쿠키에 저장할 유저 고유 식별키
        Long userId = loggedInMember.getUserId();

        //해당 postId 게시글 정보 가져오기
        PostDTO post = postService.getPostDetail(postId);

        //게시글 상세 페이지 조회시 조회수 1 상승
        postService.plusView(postId, request, response, userId);

        List<CommentDTO> commentList = commentService.getCommentList(postId);
        log.info("commentList = {}", commentList.toString());
        model.addAttribute("commentList", commentList);
        model.addAttribute("post", post);
        model.addAttribute("sessionMember", loggedInMember);
        return "post/postDetail";
    }

    //게시글 수정 페이지로 이동
    @GetMapping("/post/edit/{id}")
    public String getPostUpdate(@PathVariable("id") Long postId, Model model) {
        log.info("수정 페이지로 이동");
        PostDTO post = postService.getPostDetail(postId);
        model.addAttribute("post", post);
        return "post/postEdit";
    }

    //keyword 제목으로 검색
    @GetMapping("/post/search")
    public String searchKeyword(String keyword, @PageableDefault(sort = "postId", direction = Sort.Direction.DESC) Pageable pageable, Model model, HttpSession session){

        //게시글 제목에 keyword 들어간 게시글 불러오기
        Page<PostDTO> postList = postService.searchPosts(keyword, pageable);
        model.addAttribute("nowPage", postList.getNumber() + 1); // 현재 페이지 번호
        model.addAttribute("startPage", Math.max(postList.getNumber() - 4, 1)); // 시작 페이지 번호
        model.addAttribute("endPage", Math.min(postList.getNumber() + 5, postList.getTotalPages())); // 끝 페이지 번호
        model.addAttribute("posts", postList);
        return "post/search";
    }
}
