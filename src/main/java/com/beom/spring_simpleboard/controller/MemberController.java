package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    //Spring Security는 추후 프로젝트에 적용 에정

    private final MemberService memberService;

    //회원가입
    //다음할것 유효성 검사 추가하기
    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberDTO memberDTO){

        memberService.createMember(memberDTO);

        return "redirect:/";
    }
}
