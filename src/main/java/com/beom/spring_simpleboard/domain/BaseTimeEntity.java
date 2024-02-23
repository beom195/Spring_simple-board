package com.beom.spring_simpleboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass //Post Entity가 날짜 필드를 Column으로 인식
@EntityListeners(AuditingEntityListener.class) // auditing 기능 추가
public class BaseTimeEntity {

    //DateTimeFormatter사용 위해 LocalDateTime -> String 변환

    @Column(name = "post_created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "post_modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @PrePersist //해당 엔티티 저장하기 전
    void onPrePersist(){
        this.createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate = createdDate;
    }

    @PreUpdate //해당 엔티티 수정하기 전
    void onPreUpdate(){
        this.modifiedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}