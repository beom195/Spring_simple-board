package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> viewAllPosts();

    void savePost(PostDTO postDTO);
}
