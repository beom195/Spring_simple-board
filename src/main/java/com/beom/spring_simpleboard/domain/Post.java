package com.beom.spring_simpleboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    //조회수 기본값 0으로 설정
    @Column(name = "post_view" , columnDefinition = "integer default 0", nullable = false)
    private Integer view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    //글 삭제시 게시글에 달린 댓글도 같이 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("commentId asc") // 댓글 정렬
    private List<Comment> comments;

    @Builder
    public Post(Long postId, String title, String content, Integer view, Member member) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.view = view;
        this.member = member;
    }

    //게시글 수정하기
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
