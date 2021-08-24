package com.j2kb.minipetpee.api.member.repository;

import com.j2kb.minipetpee.api.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 멤버 이름 검색(유사 이름) + 이름 오름차순 정렬
//    Page<Member> findAllByProfile_NameLikeOrderByProfile_NameAsc(String name, Pageable pageable);

    @Query("select m from Member m where m.profile.name = :name")
    Page<Member> findAllByName(String name, Pageable pageable);
}
