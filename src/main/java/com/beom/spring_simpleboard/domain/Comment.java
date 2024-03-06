package com.beom.spring_simpleboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @OnDelete(action = OnDeleteAction.CASCADE) //게시글 삭제시 관련 댓글 삭제
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OnDelete(action = OnDeleteAction.CASCADE) //회원 탈퇴시 관련 댓글 삭제
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(name = "comment")
    private String comment;

    @Builder
    public Comment(Long commentId, Post post, Member member, String comment) {
        this.commentId = commentId;
        this.post = post;
        this.member = member;
        this.comment = comment;
    }
}
