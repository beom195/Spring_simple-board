package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDTO {

    private Long userId;
    private String userLoginId;
    private String userPassword;
    private String userName;
    private String userEmail;

    @Builder
    public UserDTO(Long userId, String userLoginId, String userPassword, String userName, String userEmail) {
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
