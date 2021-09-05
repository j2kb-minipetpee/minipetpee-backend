package com.j2kb.minipetpee.api.star.repository;


import com.j2kb.minipetpee.api.star.domain.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface StarRepository extends JpaRepository<Star, Long> {
    // 스타 목록 조회
    Page<Star> findByFanMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 팬 목록 조회
    Page<Star> findByStarMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);
}
