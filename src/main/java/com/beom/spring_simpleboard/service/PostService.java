package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
//    List<PostDTO> viewAllPosts();

    void savePost(PostDTO postDTO);

    PostDTO getPostDetail(Long postId);

    void postUpdate(Long postId, PostDTO postDTO);

    void postDelete(Long postId);

    void plusView(Long postId, HttpServletRequest request, HttpServletResponse response, Long userId);

    Page<Post> postList(Pageable pageable);
}
