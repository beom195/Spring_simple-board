package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.dto.PostDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface PostService {
    List<PostDTO> viewAllPosts();

    void savePost(PostDTO postDTO);

    PostDTO getPostDetail(Long postId);

    void postUpdate(Long postId, PostDTO postDTO);

    void postDelete(Long postId);

    void plusView(Long postId, HttpServletRequest request, HttpServletResponse response, Long userId);
}
