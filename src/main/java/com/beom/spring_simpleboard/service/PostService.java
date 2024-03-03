package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> viewAllPosts();

    void savePost(PostDTO postDTO);

    PostDTO getPostDetail(Long postId);

    void postUpdate(Long postId, PostDTO postDTO);

    void postDelete(Long postId);

    void plusView(Long postId);
}
