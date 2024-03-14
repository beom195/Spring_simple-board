package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    //회원 생성
    @Transactional
    @Override
    public void createMember(MemberDTO memberDTO) {
        memberRepository.save(memberDTO.toEntity());
    }


    //로그인
    @Transactional
    @Override
    public MemberLoginDTO login(MemberLoginDTO memberLoginDTO) {

        Member member = memberRepository.findByUserLoginId(memberLoginDTO.getUserLoginId()).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        //회원 정보를 찾아서 비밀번호가 맞을 경우 로그인
        if (member.getUserPassword().equals(memberLoginDTO.getUserPassword())) {
            return MemberLoginDTO.builder().userId(member.getUserId()).userLoginId(member.getUserLoginId()).userPassword(member.getUserPassword()).userName(member.getUserName()).build();
        }
        else {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀립니다.");
        }
    }
}
