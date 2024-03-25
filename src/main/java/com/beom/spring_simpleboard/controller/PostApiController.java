package com.beom.spring_simpleboard.controller;

import com.beom.spring_simpleboard.dto.MemberLoginDTO;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    //게시글 작성
    @PostMapping("/post/write")
    public ResponseEntity<PostDTO> writePost(@RequestBody PostDTO postDTO, HttpSession session) {

        //로그인한 member session 정보 가져오기
        MemberLoginDTO loggedInMember = (MemberLoginDTO) session.getAttribute("member");

        //게시글 작성시 작성자 정보 -> postDTO
        if (loggedInMember != null) {
            postDTO.setMember(loggedInMember.toEntity());
        }

        postService.savePost(postDTO);

        return ResponseEntity.ok(postDTO);
    }

    //게시글 수정하기
    @PostMapping("/post/edit/{postId}")
    public ResponseEntity<Long> postUpdate(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        postService.postUpdate(postId, postDTO);
        return ResponseEntity.ok(postId);
    }

    //게시글 삭제하기
    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity<Long> postDelete(@PathVariable Long postId) {
        postService.postDelete(postId);
        return ResponseEntity.ok(postId);
    }

//    //keyword 제목으로 검색
//    @GetMapping("/post/search")
//    public String searchKeyword(String keyword, @PageableDefault(sort = "postId", direction = Sort.Direction.DESC) Pageable pageable, Model model){
//
//        //게시글 제목에 keyword 들어간 게시글 불러오기
//        Page<PostDTO> postList = postService.searchPosts(keyword, pageable);
//        model.addAttribute("nowPage", postList.getNumber() + 1); // 현재 페이지 번호
//        model.addAttribute("startPage", Math.max(postList.getNumber() - 4, 1)); // 시작 페이지 번호
//        model.addAttribute("endPage", Math.min(postList.getNumber() + 5, postList.getTotalPages())); // 끝 페이지 번호
//        model.addAttribute("posts", postList);
//        return "post/search";
//    }
}
