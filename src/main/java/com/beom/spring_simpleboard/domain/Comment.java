package com.beom.spring_simpleboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long commentId;


    @ManyToOne
    @JoinColumn(name = "post_id")
    Post postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Member userId;

    @Column(name = "comment")
    String comment;



}
