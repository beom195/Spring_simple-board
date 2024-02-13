package com.beom.spring_simpleboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    //Spring Security는 추후 프로젝트에 적용 에정

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }

    //회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(){
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
}
