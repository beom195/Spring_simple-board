package com.beom.spring_simpleboard.domain;


import com.beom.spring_simpleboard.repository.TestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    TestRepository testRepository;

    @Test
    void saveUser(){
        User saveUser = User.builder()
                .userLoginId("abc123")
                .userPassword("1234")
                .userName("jjb")
                .userEmail("jjb@abc.com")
                .build();
        User savedUser = testRepository.save(saveUser);
        Assertions.assertThat(savedUser.getUserLoginId().equals("abc123"));
    }



}
