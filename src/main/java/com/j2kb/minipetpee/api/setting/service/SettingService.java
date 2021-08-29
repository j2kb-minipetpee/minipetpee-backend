package com.j2kb.minipetpee.api.setting.service;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateSettingRequest;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class SettingService {

    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;
    private final HomepeeRepository homepeeRepository;

    // 프로필 조회 - 설정 가능한 계정 정보 및 탭 목록 조회
    public Member findSettingByHomepeeId(Long homepeeId) {
        Member member = memberRepository.findByHomepeeId(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7001));
        Tab tab = tabRepository.findByHomepeeId(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7002));
        // 고민중
        return member;
    }

    // 프로필 변경
    public void updateSettings(Long homepeeId, UpdateSettingRequest settingRequest) {
        Member member = memberRepository.findByHomepeeId(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7001));
        Homepee homepee = homepeeRepository.findById(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7003));
        member.updateProfile(settingRequest.getProfile());
        homepee.update(settingRequest.getHomepee());
    }

    // 탭 목록 조회
}
