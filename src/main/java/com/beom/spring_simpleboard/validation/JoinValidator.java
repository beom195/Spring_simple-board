package com.beom.spring_simpleboard.validation;

import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {

        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDTO memberDTO = (MemberDTO) target;

        log.info("memberDTO.getUserLoginId = {}", memberDTO.getUserLoginId());

        //회원가입 아이디 중복 체크
        if(memberRepository.existsByUserLoginId(memberDTO.getUserLoginId())){
            log.info("memberDTO.userLoginId = {}", memberDTO.getUserLoginId());
            errors.rejectValue("userLoginId", "joinMember.existsUserLoginId");
        }

        //회원가입 이름 중복 체크
        if(memberRepository.existsByUserName(memberDTO.getUserName())){
            log.info("memberDTO.userName = {}", memberDTO.getUserName());
            errors.rejectValue("userName", "joinMember.existsUserName");
        }

        //회원가입 이메일 중복 체크
        if(memberRepository.existsByUserEmail(memberDTO.getUserEmail())){
            log.info("memberDTO.userEmail = {}", memberDTO.getUserEmail());
            errors.rejectValue("userEmail", "joinMember.existsUserEmail");
        }
    }
}
