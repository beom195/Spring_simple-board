package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.repository.MemberRepository;
import jakarta.transaction.Transactional;
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
    public Optional<MemberDTO> login(MemberDTO memberDTO) {


        Optional<Member> memberOptional = memberRepository.findByUserLoginId(memberDTO.getUserLoginId());

        if (memberOptional.isPresent() && memberOptional.get().getUserPassword().equals(memberDTO.getUserPassword())) {
            Member loggedInMember = memberOptional.get();
            MemberDTO memberToDTO = MemberDTO.builder()
                    .userId(loggedInMember.getUserId())
                    .userLoginId(loggedInMember.getUserLoginId())
                    .userPassword(loggedInMember.getUserPassword())
                    .userName(loggedInMember.getUserName())
                    .userEmail(loggedInMember.getUserEmail())
                    .build();
            return Optional.of(memberToDTO);
        }
        else {
            return Optional.empty();
        }
    }
}
