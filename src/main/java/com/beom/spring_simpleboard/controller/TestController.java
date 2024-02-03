package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final MemberService memberService;

    //crud 작업 테스트(postman 활용)
    @GetMapping("")
    public List<MemberDTO> getUserList() {
        return memberService.getMemberList();
    }

    @PostMapping("test/createmember")
    public void createMember(@RequestBody MemberDTO memberDTO){
        memberService.createMember(memberDTO);
    }

    @PostMapping("test/updatemember/{id}")
    public void updateMember(@PathVariable ("id") Long id,@RequestBody MemberDTO memberDTO){
        memberService.updateMember(id, memberDTO);
    }

    @PostMapping("test/deletemember/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }


}
