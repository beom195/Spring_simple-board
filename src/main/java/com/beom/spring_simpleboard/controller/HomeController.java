package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    //Spring Security는 추후 프로젝트에 적용 에정

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String login(MemberLoginDTO memberLoginDTO, Model model){
        model.addAttribute("loginMember", memberLoginDTO);
        return "member/login";
    }

    //회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(MemberDTO memberDTO, Model model){
        model.addAttribute("joinMember", memberDTO);
        return "member/join";
    }

    //로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session){

        // session 로그아웃 처리
        if(session != null){
            session.invalidate();
        }
        log.info("로그아웃 했습니다.");
        return "redirect:/";
    }

    //글쓰기 폼으로 이동
    @GetMapping("/post/write")
    public String write(HttpSession session, Model model){
        //로그인한 session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        model.addAttribute("loggedInMember", loggedInMember);
        log.info("글쓰기 페이지로 이동");
        return "post/write";
    }

    //게시글 상세 페이지로 이동
    @GetMapping("/post/detail/{id}")
    public String getPostDetail(@PathVariable("id") Long postId, Model model, HttpSession session){

        //해당 postId 게시글 정보 가져오기
        PostDTO post = postService.getPostDetail(postId);
        //게시글 상세 페이지 조회시 조회수 1 상승
        postService.plusView(postId);

        //session에 저장된 member 정보가 일치하면 수정과 삭제 버튼을 보이게하기 위해 member session 받아오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        model.addAttribute("post", post);
        model.addAttribute("sessionMember", loggedInMember);
        return "post/postDetail";
    }
}
