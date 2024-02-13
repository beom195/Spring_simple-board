package com.beom.spring_simpleboard.service;


import com.beom.spring_simpleboard.dto.MemberDTO;

import java.util.Optional;

public interface MemberService {

    //회원가입
    void createMember(MemberDTO memberDTO);

    //로그인
    Optional<MemberDTO> login(MemberDTO memberDTO);
}
