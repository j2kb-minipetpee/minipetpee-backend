package com.j2kb.minipetpee.api.member.domain.repository;

import com.j2kb.minipetpee.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
