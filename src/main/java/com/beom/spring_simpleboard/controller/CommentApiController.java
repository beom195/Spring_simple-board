package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.CommentDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/commentWrite/{postId}")
    public ResponseEntity<Long> writeComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO, HttpSession session){
        //로그인한 member session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");
        log.info("commnetDTO.getComment = {]" , commentDTO.getComment());
        return ResponseEntity.ok(commentService.writeComment(postId, commentDTO, loggedInMember.getUserId()));
    }

    //댓글 수정
    @PostMapping("/commentUpdate/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO){
        log.info("commentDTO = {}", commentDTO.toString());
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok(commentId);
    }

    //댓글 삭제
    @DeleteMapping("/commentDelete/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO){
        commentService.deleteComment(commentId, commentDTO);
        return ResponseEntity.ok(commentId);
    }

    //    @PostMapping("/post/{postId}/comment")
//    public String writeComment(@PathVariable Long postId, CommentDTO commentDTO, HttpSession session){
//
//        //로그인한 member session 정보 가져오기
//        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");
//        commentService.writeComment(postId, commentDTO,loggedInMember.getUserId());
//
//        return "redirect:/post/detail/" + postId;
//    }
}
