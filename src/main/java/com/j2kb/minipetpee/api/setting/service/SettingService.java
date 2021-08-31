package com.j2kb.minipetpee.api.setting.service;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.controller.dto.request.TabRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateSettingRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateTabsRequest;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
public class SettingService {

    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;
    private final HomepeeRepository homepeeRepository;

    // 프로필 조회 - 설정 가능한 계정 정보 및 탭 목록 조회
    @Transactional(readOnly = true)
    public Member findMemberByHomepeeId(Long homepeeId) {
        Homepee homepee = findHomepeeById(homepeeId);
        Member member = memberRepository.findByHomepeeId(homepee.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7001));
        return member;
    }

    public List<Tab> findTabsByHomepeeId(Long homepeeId) {
        Homepee homepee = findHomepeeById(homepeeId);
        List<Tab> tabs = tabRepository.findAllByHomepeeId(homepee.getId());
        return tabs;
    }

    // 프로필 변경
    public void updateSettings(Long homepeeId, UpdateSettingRequest settingRequest) {
        Homepee homepee = findHomepeeById(homepeeId);
        Member member = memberRepository.findByHomepeeId(homepee.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7001));
        member.updateProfile(settingRequest.getProfile());
        homepee.update(settingRequest.getHomepee());
    }

    // 탭 공개 여부 변경
    public void updateTabs(Long homepeeId, UpdateTabsRequest updateTabsRequest){
        // 홈피 확인
        Homepee homepee = findHomepeeById(homepeeId);

        // tab id Set 생성
        Set<Long> tabIdSet = tabRepository.findAllByHomepeeId(homepee.getId())
                .stream()
                .map((tab) -> tab.getId())
                .collect(Collectors.toSet());

        for (TabRequest tabRequest : updateTabsRequest.getTabs()){
            // 홈피가 가진 탭이 맞는지 확인
            if (!tabIdSet.contains(tabRequest.getId())){
                new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7014);
            }
            Tab tab = tabRepository.findById(tabRequest.getId())
                    .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP7004));
            tab.updateVisibility(tabRequest.isVisible());
        }
    }

    // 홈피 조회 (유효성 검사용)
    public Homepee findHomepeeById(Long homepeeId) {
        Homepee homepee = homepeeRepository.findById(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP7003));
        return homepee;
    }

}
