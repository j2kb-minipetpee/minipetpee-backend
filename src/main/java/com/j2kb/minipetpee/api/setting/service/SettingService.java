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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class SettingService {

    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;
    private final HomepeeRepository homepeeRepository;

    @Transactional(readOnly = true)
    public List<Tab> findTabsByHomepeeId(Long homepeeId) {
        Homepee homepee = findHomepeeById(homepeeId);
        return tabRepository.findAllByHomepeeId(homepee.getId());
    }

    // 프로필 변경
    @Transactional
    public void updateSettings(Long homepeeId, UpdateSettingRequest settingRequest) {
        Homepee homepee = findHomepeeById(homepeeId);
        Member member = memberRepository.findByHomepeeId(homepee.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7001));
        member.updateProfile(settingRequest.toProfile());
        homepee.updateTitle(settingRequest.getHomepee().getTitle());
        homepee.updateGateImageUrl(settingRequest.getHomepee().getGateImageUrl());
    }

    // 탭 공개 여부 변경
    @Transactional
    public void updateTabs(Long homepeeId, UpdateTabsRequest updateTabsRequest){
        // 홈피 확인
        Homepee homepee = findHomepeeById(homepeeId);

        for (TabRequest tabRequest : updateTabsRequest.getTabs()){
            Tab tab = tabRepository.findById(tabRequest.getId())
                    .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP7004));
            tab.updateVisibility(tabRequest.isVisible());
        }
    }

    // 홈피 조회 (유효성 검사용)
    // mandatory 전파 옵션: 부모 트랜잭션에 합류, 부모 트랜잭션 없으면 예외
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public Homepee findHomepeeById(Long homepeeId) {
        Homepee homepee = homepeeRepository.findById(homepeeId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP7003));
        return homepee;
    }

}
