package com.beom.spring_simpleboard.service;


import com.beom.spring_simpleboard.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    public List<MemberDTO> getMemberList();

    public void createMember(MemberDTO memberDTO);

    public void updateMember(Long id, MemberDTO memberDTO);

    public void deleteMember(Long id);
}
