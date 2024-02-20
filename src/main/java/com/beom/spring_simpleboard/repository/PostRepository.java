package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.Post;
import com.beom.spring_simpleboard.dto.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}
