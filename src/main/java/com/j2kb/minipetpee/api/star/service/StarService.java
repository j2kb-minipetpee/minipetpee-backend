package com.j2kb.minipetpee.api.star.service;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.star.domain.Star;
import com.j2kb.minipetpee.api.star.repository.StarRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StarService {

    private final StarRepository starRepository;
    private final MemberRepository memberRepository;

    // 스타
    @Transactional
    public void saveStar(Long currentMemberId, Long fanMemberId, Long starMemberId) {
        // 팬 계정 검사
        Member fanMember = memberRepository.findById(fanMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8003));

        // 로그인한 계정과 일치 여부 검사
        if (!currentMemberId.equals(fanMember.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP0000);
        }

        // 스타 계정 검사
        Member starMember = memberRepository.findById(starMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001));
        Star star = new Star(starMember, fanMember);

        // 저장
        starRepository.save(star);
    }

    // 언스타
    @Transactional
    public void deleteStar(Long currentMemberId, Long fanMemberId, Long starMemberId) {
        // 로그인한 계정과 팬 계정 일치 여부 검사
        if (!currentMemberId.equals(fanMemberId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP0000);
        }

        // 스타 관계 불러오기
        Star star = starRepository.findByFanMemberIdAndStarMemberId(currentMemberId, starMemberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8006));

        // 삭제
        starRepository.delete(star);
    }

    // 스타 여부 확인
    @Transactional(readOnly = true)
    public boolean hasStarRelationship(Long currentMemberId, Long fanMemberId, Long starMemberId) {
        // 로그인 계정 == 팬 계정 여부 체크
        if (!currentMemberId.equals(fanMemberId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP0000);
        }
        // 스타(팔로잉) 여부 확인
        return (starRepository.countByFanMemberIdAndStarMemberId(currentMemberId, starMemberId) != 0);
    }

    // 스타 목록 조회
    @Transactional(readOnly = true)
    public Page<Star> findStars(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8007));
        return starRepository.findByFanMemberId(member.getId(), pageable);
    }

    // 팬 목록 조회
    @Transactional(readOnly = true)
    public Page<Star> findFans(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8008));
        return starRepository.findByStarMemberId(member.getId(), pageable);
    }
}

