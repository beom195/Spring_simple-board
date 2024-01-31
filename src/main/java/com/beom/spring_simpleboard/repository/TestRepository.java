package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TestRepository extends JpaRepository<User, Long> {



}
