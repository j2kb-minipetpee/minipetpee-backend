package com.j2kb.minipetpee.api.member.repository;

import com.j2kb.minipetpee.api.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 멤버 이름 검색(유사 이름) + 이름 오름차순 정렬
    // @Query("SELECT m From Member m WHERE m.profile.name LIKE '%:name%'")
    Page<Member> findMembersByProfileNameContainingOrderByProfileNameAsc(String name, Pageable pageable);

    Optional<Member> findByHomepeeId(Long homepeeId);
  
    Optional<Member> findByEmail(String email);
}
