package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Comment;
import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class CommentDTO {

    private Long commentId;

    private String comment;

    private String createdDate;

    private String modifiedDate;

    private Member member;
    private Post post;

    @Builder
    public CommentDTO(Long commentId, String comment, String createdDate, String modifiedDate, Member member, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.member = member;
        this.post = post;
    }

    public Comment toEntity() {
        return Comment.builder().commentId(commentId).comment(comment).member(member).post(post).build();
    }
}
