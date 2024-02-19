package com.beom.spring_simpleboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MemberLoginDTO {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userLoginId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPassword;

    private String userName;

    @Builder
    public MemberLoginDTO(String userLoginId, String userPassword, String userName){
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
