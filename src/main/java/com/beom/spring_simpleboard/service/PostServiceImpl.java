package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.repository.PostRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //게시글 불러오기
    @Transactional
    @Override
    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    //게시글 작성
    @Transactional
    @Override
    public void savePost(PostDTO postDTO) {

        postRepository.save(postDTO.toEntity());
    }

    //게시글 상세 페이지
    @Transactional
    @Override
    public PostDTO getPostDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));


        return PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(post.getView())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .member(post.getMember())
                .build();
    }

    //게시글 수정하기
    @Transactional
    @Override
    public void postUpdate(Long postId, PostDTO postDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        post.update(postDTO.getTitle(), postDTO.getContent());
    }

    //게시글 삭제하기
    @Transactional
    @Override
    public void postDelete(Long postId) {
        postRepository.deleteById(postId);
    }

    //조회수 증가
    @Transactional
    @Override
    public void plusView(Long postId, HttpServletRequest request, HttpServletResponse response, Long userId) {

        //쿠키 가져오기
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        boolean checkCookie = false;
        String cookieValue = "[" + userId + "-" + postId + "]";

        //cookies에 cookie가 있는지 확인
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            //쿠키에 "alreadyView"라는 값이 있을때
            if (cookies[i].getName().equals("alreadyView")) {
                cookie = cookies[i];
                //쿠키 값에 해당 회원 번호와 게시글 번호가 없을때
                if (!cookie.getValue().contains(cookieValue)) {
                    log.info("조회수 증가");
                    postRepository.plusView(postId);
                    cookie.setValue(cookie.getValue() + cookieValue);
                }
                checkCookie = true;
                break;
            }
        }

        //checkCookie가 false일 경우 즉 쿠키에 값이 없을 경우
        if (!checkCookie) {
            log.info("조회수 증가");
            postRepository.plusView(postId);
            cookie = new Cookie("alreadyView", cookieValue);
        }
        cookie.setMaxAge(60 * 60 * 24); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        response.addCookie(cookie); //response에 Cookie 추가
    }

    //게시글 검색하기
    @Transactional
    @Override
    public Page<Post> searchPosts(String keyword, Pageable pageable) {

        Page<Post> posts = postRepository.findByTitleContaining(keyword, pageable);
        return posts;
    }


}
