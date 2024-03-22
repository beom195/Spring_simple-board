package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.service.CommentService;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final CommentService commentService;

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


}
