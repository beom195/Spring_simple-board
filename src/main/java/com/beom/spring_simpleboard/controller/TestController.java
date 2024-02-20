//package com.beom.spring_simpleboard.controller;
//
//import com.beom.spring_simpleboard.dto.MemberDTO;
//import com.beom.spring_simpleboard.service.MemberService;
//import com.beom.spring_simpleboard.service.TestService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/test")
//public class TestController {
//
//    private final TestService testService;
//
//    //crud 작업 테스트(postman 활용)
//    @GetMapping("")
//    public List<MemberDTO> getMemberList() {
//        return testService.getMemberList();
//    }
//
//
//    @PostMapping("test/updatemember/{id}")
//    public void updateMember(@PathVariable ("id") Long id,@RequestBody MemberDTO memberDTO){
//        testService.updateMember(id, memberDTO);
//    }
//
//    @PostMapping("test/deletemember/{id}")
//    public void deleteMember(@PathVariable Long id){
//        testService.deleteMember(id);
//    }
//
//
//}
