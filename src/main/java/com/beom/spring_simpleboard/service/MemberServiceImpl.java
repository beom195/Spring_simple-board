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
    public Optional<MemberLoginDTO> login(MemberLoginDTO memberLoginDTO) {

        Optional<Member> memberOptional = memberRepository.findByUserLoginId(memberLoginDTO.getUserLoginId());

        if (memberOptional.isPresent() && memberOptional.get().getUserPassword().equals(memberLoginDTO.getUserPassword())) {
            Member loggedInMember = memberOptional.get();
            MemberLoginDTO memberToDTO = MemberLoginDTO.builder()
                    .userId(loggedInMember.getUserId())
                    .userLoginId(loggedInMember.getUserLoginId())
                    .userPassword(loggedInMember.getUserPassword())
                    .userName(loggedInMember.getUserName())
                    .build();
            return Optional.of(memberToDTO);
        }
        else {
            return Optional.empty();
        }
    }
}
