package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.CommentDTO;
import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public String writeComment(@PathVariable Long postId, CommentDTO commentDTO, HttpSession session){

        //로그인한 member session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");
        commentService.writeComment(postId, commentDTO,loggedInMember.getUserId());

        return "redirect:/post/detail/" + postId;
    }

    @PostMapping("/comment/{commentId}")
    @ResponseBody
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok(commentId);
    }

}
