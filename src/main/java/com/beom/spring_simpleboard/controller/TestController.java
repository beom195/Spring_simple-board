package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;

    //crud 작업 테스트
    @GetMapping("test")
    public List<MemberDTO> getUserList() {
        return memberService.getMemberList();
    }

    @PostMapping("test/createmember")
    public void createMember(@RequestBody MemberDTO memberDTO){
        memberService.createMember(memberDTO);
    }

}
