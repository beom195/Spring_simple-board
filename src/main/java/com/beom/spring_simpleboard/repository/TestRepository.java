package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TestRepository extends JpaRepository<Member, Long> {

}
