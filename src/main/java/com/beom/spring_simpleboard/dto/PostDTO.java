package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String createdDate, modifiedDate;
    private int view;
    private Member member;

    @Builder
    public PostDTO(Long postId, String title, String content, String createdDate, String modifiedDate, int view, Member member) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.view = view;
        this.member = member;
    }

    public Post toEntity(){
        return Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .view(view)
                .member(member)
                .build();
    }
}
