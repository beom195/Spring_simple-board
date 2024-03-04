package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import com.beom.spring_simpleboard.repository.PostRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //전체 게시글 불러오기
    @Transactional(readOnly = true)
    @Override
    public List<PostDTO> viewAllPosts() {

        List<Post> postList = postRepository.findAll();


        //Entity -> DTO
        return postList.stream().map(posts -> PostDTO.builder().postId(posts.getPostId()).title(posts.getTitle()).content(posts.getContent()).view(posts.getView()).createdDate(posts.getCreatedDate()).modifiedDate(posts.getModifiedDate()).member(posts.getMember()).build()).collect(Collectors.toList());
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
        Optional<Post> postOptional = postRepository.findById(postId);


        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostDTO postDetail = PostDTO.builder().postId(post.getPostId()).title(post.getTitle()).content(post.getContent()).view(post.getView()).createdDate(post.getCreatedDate()).modifiedDate(post.getModifiedDate()).member(post.getMember()).build();

            return postDetail;
        }
        return null;
    }

    //게시글 수정하기
    @Transactional
    @Override
    public void postUpdate(Long postId, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(postId);

        Post post = postOptional.get();
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
        for(int i = 0; cookies != null && i < cookies.length; i++){
            //쿠키에 "alreadyView"라는 값이 있을때
            if(cookies[i].getName().equals("alreadyView")){
                cookie = cookies[i];
                //쿠키 값에 해당 회원 번호와 게시글 번호가 없을때
                if(!cookie.getValue().contains(cookieValue)){
                    log.info("조회수 증가");
                    postRepository.plusView(postId);
                    cookie.setValue(cookie.getValue() + cookieValue);
                }
                checkCookie = true;
                break;
            }
        }

        //checkCookie가 false일 경우 즉 쿠키에 값이 없을 경우
        if(!checkCookie){
            log.info("조회수 증가");
            postRepository.plusView(postId);
            cookie = new Cookie("alreadyView", cookieValue);
        }
        cookie.setMaxAge(60*60*24); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        response.addCookie(cookie); //response에 Cookie 추가
    }

}
