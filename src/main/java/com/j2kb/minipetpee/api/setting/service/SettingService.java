package com.j2kb.minipetpee.api.setting.controller;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SettingService {

    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;

    public Member findProfileByHomepeeId(Long homepeeId) {
        return memberRepository.findByHomepeeId(homepeeId)
                .orElseThrow(() -> { new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP9001); });
    }
}
