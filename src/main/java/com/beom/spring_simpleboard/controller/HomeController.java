package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class HomeController {

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


}
