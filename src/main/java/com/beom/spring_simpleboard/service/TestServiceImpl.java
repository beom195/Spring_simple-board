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
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    //전체 회원 불러오기
    @Transactional
    @Override
    public List<MemberDTO> getMemberList() {
        List<Member> memberList = testRepository.findAll();
        return memberList.stream().map(member -> MemberDTO.builder().userId(member.getUserId()).userLoginId(member.getUserLoginId()).userPassword(member.getUserPassword()).userName(member.getUserName()).userEmail(member.getUserEmail()).build()).collect(Collectors.toList());
    }

    //회원 수정
    @Transactional
    @Override
    public void updateMember(Long id, MemberDTO memberDTO) {
        Optional<Member> userId = testRepository.findById(id);
        userId.ifPresent(member -> member.updateMember(memberDTO.getUserPassword(), memberDTO.getUserName(), memberDTO.getUserEmail()));
    }

    //회원 삭제
    @Transactional
    @Override
    public void deleteMember(Long id) {
        testRepository.deleteById(id);
    }
}
