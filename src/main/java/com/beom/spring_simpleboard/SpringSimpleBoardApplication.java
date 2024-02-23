package com.beom.spring_simpleboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSimpleBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSimpleBoardApplication.class, args);
    }

}
