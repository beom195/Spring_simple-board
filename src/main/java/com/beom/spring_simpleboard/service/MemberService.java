package com.beom.spring_simpleboard.service;


import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    //회원가입
    public void createMember(MemberDTO memberDTO);

    //로그인
    Optional<MemberDTO> login(MemberDTO memberDTO);
}
