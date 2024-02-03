package com.beom.spring_simpleboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
