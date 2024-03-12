package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentList(Long postId);

    Long writeComment(Long postId,CommentDTO commentDTO, Long userId);

    void updateComment(Long commentId, CommentDTO commentDTO);

    void deleteComment(Long commentId, CommentDTO commentDTO);
}
