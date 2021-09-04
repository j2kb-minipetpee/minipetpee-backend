package com.j2kb.minipetpee.api.star.service;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.MemberAdapter;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.star.controller.dto.request.StarRequest;
import com.j2kb.minipetpee.api.star.domain.Star;
import com.j2kb.minipetpee.api.star.repository.StarRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StarService {

    private final StarRepository starRepository;
    private final MemberRepository memberRepository;

    // 스타
    @Transactional
    public void saveStar(MemberAdapter memberAdapter, StarRequest starRequest) {
        // 로그인한 계정(팬) 정보 검사
        if (Objects.isNull(memberAdapter.getMember())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8003);
        }
        Member fanMember = memberAdapter.getMember();

        // 스타 계정 검사
        Member starMember = memberRepository.findById(starRequest.getStarMemberId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001));
        Star star = new Star(starMember, fanMember);

        // 저장
        starRepository.save(star);
    }

    // 언스타
    @Transactional
    public void deleteStar(Long starId) {
        // 스타 관계 불러오기
        Star star = starRepository.findById(starId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8006));
        // 스타에 저장된 팬 정보와 로그인한 정보가 일치하지 않을 경우 처리

        // 삭제
        starRepository.delete(star);
    }

    // 스타 목록 조회
    @Transactional(readOnly = true)
    public void findStars() {

    }

    // 팬 목록 조회
    @Transactional(readOnly = true)
    public void findFans() {

    }
}

