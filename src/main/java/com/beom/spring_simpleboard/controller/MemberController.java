package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public String joinMember(@Validated MemberDTO memberDTO){

        memberService.createMember(memberDTO);

//        return "redirect:/";
        return "member/login";
    }

    //spring security는 추후 다음 프로젝트에 적용 예정
    @PostMapping("/login")
    public String loginMember(HttpSession session, MemberDTO memberDTO){

        Optional<MemberDTO> member = memberService.login(memberDTO);

        //login 실패시 login.html 유지
        if(member.isEmpty()){
            log.info("아이디 또는 비밀번호가 틀립니다");
                return "member/login";
            }

        MemberDTO loggedInMember = member.get();
        log.info("userLoginId = {}", loggedInMember.getUserLoginId());
        log.info("userPassword = {}", loggedInMember.getUserPassword());

        //로그인 정보 session에 저장
        session.setAttribute("member", loggedInMember);

//        session 만료 기간 10분 설정
        session.setMaxInactiveInterval(600);

        //login 성공시 index.html로 이동
        return "redirect:/";
    }

}
