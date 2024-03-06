package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentList(Long postId);
}
