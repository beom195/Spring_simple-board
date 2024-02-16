package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MemberDTO {

    private Long userId;


    @NotBlank
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{5,10}$", message = "아이디는 특수문자를 제외한 5~10자리여야 합니다.")
    private String userLoginId;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String userPassword;

    @NotBlank
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String userName;

    @NotBlank
    @Email(message = "이메일 형식이 아닙니다.")
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
