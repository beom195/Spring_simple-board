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


    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{5,10}$")
    private String userLoginId;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String userPassword;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$")
    private String userName;

    @NotBlank(message = "필수 정보입니다.")
    @Email
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
