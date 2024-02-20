package com.beom.spring_simpleboard.dto;

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
    private LocalDateTime createdDate;
    private int view;

    @Builder
    public PostDTO(Long postId, String title, String content, LocalDateTime createdDate, int view) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.view = view;
    }

    public Post toEntity(){
        return Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .view(view)
                .build();
    }
}
