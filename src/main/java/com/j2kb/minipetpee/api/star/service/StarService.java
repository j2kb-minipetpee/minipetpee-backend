package com.j2kb.minipetpee.api.star.service;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.star.domain.Relationship;
import com.j2kb.minipetpee.api.star.domain.Star;
import com.j2kb.minipetpee.api.star.repository.StarRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StarService {

    private final StarRepository starRepository;
    private final MemberRepository memberRepository;

    // 스타 여부 확인
    @Transactional(readOnly = true)
    public Relationship checkStarRelationship(Long fanMemberId, Long starMemberId) {
        Relationship relationship;
        if (Objects.isNull(fanMemberId)) {
            relationship = Relationship.NONE;
        } else if (fanMemberId.equals(starMemberId)) { // 본인 홈피
            relationship = Relationship.SELF;
        } else if (starRepository.countByFanMemberIdAndStarMemberId(fanMemberId, starMemberId) != 0){  // 스타(팔로잉) 여부 확인
            relationship = Relationship.STAR; // 팔로잉중
        } else { // 언팔중
            relationship = Relationship.UNSTAR;
        }
        return relationship;
    }

    // 스타
    @Transactional
    public void saveStar(Long fanMemberId, Long starMemberId) {
        // 팬 계정 검사
        Member fanMember = memberRepository.findById(fanMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8003));

        // 스타 계정 검사
        Member starMember = memberRepository.findById(starMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001));

        // 스타 중복 확인
        if (starRepository.countByFanMemberIdAndStarMemberId(fanMemberId, starMemberId) != 0) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8009);
        }

        // 저장
        Star star = new Star(starMember, fanMember);
        starRepository.save(star);
    }

    // 언스타
    @Transactional
    public void deleteStar(Long fanMemberId, Long starMemberId) {
        // 스타 관계 불러오기
        Star star = starRepository.findByFanMemberIdAndStarMemberId(fanMemberId, starMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8006));

        // 삭제
        starRepository.delete(star);
    }

    // 스타 목록 조회
    @Transactional(readOnly = true)
    public Page<Star> findStars(Long memberId, Pageable pageable) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8007));

        // 스타 목록 조회
        return starRepository.findByFanMemberId(member.getId(), pageable);
    }

    // 팬 목록 조회
    @Transactional(readOnly = true)
    public Page<Star> findFans(Long memberId, Pageable pageable) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8008));

        // 팬 목록 조회
        return starRepository.findByStarMemberId(member.getId(), pageable);
    }
}

