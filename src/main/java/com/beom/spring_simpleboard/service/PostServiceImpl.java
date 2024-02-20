package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //전체 게시글 불러오기
    @Override
    public List<PostDTO> viewAllPosts() {

        List<Post> posts = postRepository.findAll();

        //Entity -> DTO
        return posts.stream().map(post -> PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .view(post.getView())
                .build()
        ).collect(Collectors.toList());
    }
}
