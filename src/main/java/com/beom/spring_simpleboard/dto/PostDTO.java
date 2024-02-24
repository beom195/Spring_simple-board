package com.beom.spring_simpleboard.dto;

import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.domain.Post;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String createdDate, modifiedDate;
    private Integer view;

    //게시글 작성 세션 멤버 정보 가져오기 위해 setter 적용
    @Setter
    private Member member;

    @Builder
    public PostDTO(Long postId, String title, String content, String createdDate, String modifiedDate, Integer view, Member member) {
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
                .view(0)
                .member(member)
                .build();
    }
}
