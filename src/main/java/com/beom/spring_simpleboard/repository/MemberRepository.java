package com.beom.spring_simpleboard.repository;

import com.beom.spring_simpleboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserLoginId(String userLoginId);
    boolean existsByUserLoginId(String userLoginId);
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String userEmail);

}
