package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.repository.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final TestRepository testRepository;

    @Transactional
    @Override
    public List<MemberDTO> getMemberList() {
        List<Member> memberList = testRepository.findAll();
        return memberList.stream()
                .map(member -> MemberDTO.builder()
                        .userId(member.getUserId())
                        .userLoginId(member.getUserLoginId())
                        .userPassword(member.getUserPassword())
                        .userName(member.getUserName())
                        .userEmail(member.getUserEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void createMember(MemberDTO memberDTO) {
        testRepository.save(memberDTO.toEntity());
    }

    @Transactional
    @Override
    public void updateMember(Long id,MemberDTO memberDTO) {
        Optional<Member> userId = testRepository.findById(id);
        userId.ifPresent(member -> member.updateMember(memberDTO.getUserPassword(), memberDTO.getUserName(), memberDTO.getUserEmail()));
    }
}
