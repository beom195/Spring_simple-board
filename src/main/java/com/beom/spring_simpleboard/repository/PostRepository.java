package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.postId = :id")
    int plusView(@Param("id")Long postId);

    Page<Post> findByTitleContaining(String keyowrd, Pageable pageable);
}
