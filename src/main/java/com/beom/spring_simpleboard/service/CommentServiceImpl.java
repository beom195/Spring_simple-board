package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Comment;
import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.CommentDTO;
import com.beom.spring_simpleboard.repository.CommentRepository;
import com.beom.spring_simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getCommentList(Long postId) {
        //해당 게시글의 댓글 불러오기
        List<Comment> commentList = commentRepository.findCommentsWithMemberByPostId(postId);
        return commentList.stream()
                .map(comment -> CommentDTO.builder()
                        .commentId(comment.getCommentId())
                        .post(comment.getPost())
                        .member(comment.getMember())
                        .comment(comment.getComment())
                        .build())
                .collect(Collectors.toList());
    }
}
