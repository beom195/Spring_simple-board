package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //fetch join으로 n+1 해결
    @Query("SELECT c FROM Comment c JOIN FETCH c.member WHERE c.post.postId = :postId ORDER BY c.commentId ASC")
    List<Comment> findCommentsWithMemberByPostId(@Param("postId") Long postId);
}
