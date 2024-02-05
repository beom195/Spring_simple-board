package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    //Spring Security는 추후 프로젝트에 적용 에정

    private final MemberService memberService;

    //회원가입
    //다음할것 유효성 검사 추가하기
    @PostMapping("/join")
    public String joinMember(MemberDTO memberDTO){

        memberService.createMember(memberDTO);

        return "redirect:/";
    }

    //spring security는 추후 다음 프로젝트에 적용 예정
    //다음할것 세션 쿠키 적용.
    @PostMapping("/login")
    public String loginMember(MemberDTO memberDTO){

        Optional<MemberDTO> member = memberService.login(memberDTO);
        log.info("userLoginId = {}", memberDTO.getUserLoginId());
        log.info("userPassword = {}", memberDTO.getUserPassword());

        //login 실패시 login.html 유지
        if(member.isEmpty()){
                return "member/login";
            }

        //login 성공시 index.html로 이동
        return "redirect:/";
    }

}
