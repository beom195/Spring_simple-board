package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //전체 게시글 불러오기
    @Transactional(readOnly = true)
    @Override
    public List<PostDTO> viewAllPosts() {

        List<Post> postList = postRepository.findAll();


        //Entity -> DTO
        return postList.stream().map(posts -> PostDTO.builder()
                .postId(posts.getPostId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .view(posts.getView())
                .createdDate(posts.getCreatedDate())
                .modifiedDate(posts.getModifiedDate())
                .member(posts.getMember())
                .build()
        ).collect(Collectors.toList());
    }

    //게시글 작성
    @Transactional
    @Override
    public void savePost(PostDTO postDTO) {

        postRepository.save(postDTO.toEntity());
    }

    //게시글 상세 페이지
    @Transactional
    @Override
    public PostDTO getPostDetail(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        PostDTO postDetail = PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(post.getView())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .member(post.getMember())
                .build();

        return postDetail;
    }

    //게시글 수정하기
    @Transactional
    @Override
    public void postUpdate(Long postId, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(postId);

        Post post = postOptional.get();
        post.postUpdate(postDTO.getTitle(),postDTO.getContent());
    }

    //게시글 삭제하기
    @Transactional
    @Override
    public void postDelete(Long postId) {
        postRepository.deleteById(postId);
    }
}
