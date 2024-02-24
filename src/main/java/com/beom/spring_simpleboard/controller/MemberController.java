package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.service.MemberService;
import com.beom.spring_simpleboard.validation.JoinValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    private final JoinValidator joinValidator;

    //설정 유효성 검증을 위해서 Databinder 추가
    @InitBinder("memberDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(joinValidator);
    }

    //회원가입(유효성 검증)
    //회원가입 실패시 입력 데이터 유지
    @PostMapping("/join")
    public String joinMember(@Valid MemberDTO memberDTO, BindingResult errors, Model model) {

        //회원가입 실패시 데이터 유지
        model.addAttribute("memberDTO", memberDTO);

        //검증에 실패하면 다시 입력 폼으로,
        if (errors.hasErrors()) {
            log.info("errors = {}" + errors);
            return "member/join";
        }

        //검증 성공시 회원가입 로직 실행
        memberService.createMember(memberDTO);

        return "redirect:/member/login";
    }

    //로그인
    @PostMapping("/login")
    public String loginMember(@Valid MemberLoginDTO memberLoginDTO, BindingResult errors, HttpSession session, Model model) {

        //로그인 실패시 데이터 유지
        model.addAttribute("memberLoginDTO", memberLoginDTO);

        Optional<MemberLoginDTO> member = memberService.login(memberLoginDTO);

        //아이디 비밀번호가 공백일 경우
        if (errors.hasErrors()) {
            log.info("loginErrors = {}", errors);
            return "member/login";
        }
        //아이디 비밀번호가 틀릴경우
        if (member.isEmpty()) {
            log.info("아이디 또는 비밀번호가 틀립니다.");
            return "member/login";
        }

        //로그인한 Member 정보 받아오기
        MemberLoginDTO loggedInMember = member.get();

        log.info("userLoginId = {}", loggedInMember.getUserLoginId());
        log.info("userPassword = {}", loggedInMember.getUserPassword());

        //로그인 정보 session에 저장
        session.setAttribute("member", loggedInMember);

        //session 만료 기간 10분 설정
        session.setMaxInactiveInterval(600);

        //login 성공시 index.html로 이동
        return "redirect:/";
    }

}
