package com.beom.spring_simpleboard.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_login_id")
    private String userLoginId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Builder
    public Member(String userLoginId, String userPassword, String userName, String userEmail) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public void updateMember(String userPassword, String userName, String userEmail){
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
