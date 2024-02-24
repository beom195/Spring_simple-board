package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MemberLoginDTO {

    private Long userId;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userLoginId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPassword;

    private String userName;

    @Builder
    public MemberLoginDTO(Long userId, String userLoginId, String userPassword, String userName){
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .userLoginId(userLoginId)
                .userPassword(userPassword)
                .userName(userName)
                .build();
    }

}
