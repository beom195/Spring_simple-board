package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberDTO {

    private Long userId;
    private String userLoginId;
    private String userPassword;
    private String userName;
    private String userEmail;

    @Builder
    public MemberDTO(Long userId, String userLoginId, String userPassword, String userName, String userEmail) {
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Member toEntity(){
        return Member.builder()
                .userLoginId(userLoginId)
                .userPassword(userPassword)
                .userName(userName)
                .userEmail(userEmail)
                .build();
    }

}
