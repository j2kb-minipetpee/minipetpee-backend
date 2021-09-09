package com.j2kb.minipetpee.api.star.repository;


import com.j2kb.minipetpee.api.star.domain.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {

    // 스타 여부 조회
    Long countByFanMemberIdAndStarMemberId(Long fanMemberId, Long starMemberId);

// 스타 조회
    Optional<Star> findByFanMemberIdAndStarMemberId(Long fanMemberId, Long starMemberId);

    // 스타 목록 조회
    Page<Star> findByFanMemberId(Long memberId, Pageable pageable);

    // 팬 목록 조회
    Page<Star> findByStarMemberId(Long memberId, Pageable pageable);
}
