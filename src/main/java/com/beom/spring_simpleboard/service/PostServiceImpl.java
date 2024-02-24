package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.MemberDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        List<Post> posts = postRepository.findAll();


        //Entity -> DTO
        return posts.stream().map(post -> PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(post.getView())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .member(post.getMember())
                .build()
        ).collect(Collectors.toList());
    }

    //게시글 작성
    @Transactional
    @Override
    public void savePost(PostDTO postDTO) {

        postRepository.save(postDTO.toEntity());
    }
}
