package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Comment;
import com.beom.spring_simpleboard.domain.Member;
import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.CommentDTO;
import com.beom.spring_simpleboard.repository.CommentRepository;
import com.beom.spring_simpleboard.repository.MemberRepository;
import com.beom.spring_simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    //댓글 리스트 불러오기
    @Transactional(readOnly = true)
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
                        .createdDate(comment.getCreatedDate())
                        .modifiedDate(comment.getModifiedDate())
                        .build())
                .collect(Collectors.toList());
    }

    //댓글 작성
    @Transactional
    @Override
    public void writeComment(Long postId, CommentDTO commentDTO, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        CommentDTO comment = CommentDTO.builder()
                .comment(commentDTO.getComment())
                .post(post)
                .member(member)
                .createdDate(commentDTO.getCreatedDate())
                .modifiedDate(commentDTO.getModifiedDate())
                .build();
        Comment commentDTOToEntity = comment.toEntity();
        commentRepository.save(commentDTOToEntity);
    }

    //댓글 수정
    @Transactional
    @Override
    public void updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        comment.update(commentDTO.getComment());
    }

    //댓글 삭제
    @Transactional
    @Override
    public void deleteComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);

    }
}
