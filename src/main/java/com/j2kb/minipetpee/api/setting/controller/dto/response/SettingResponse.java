package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SettingResponse {
    private final SettingProfileResponse profile;
    private final SettingHomepeeResponse homepee;
    private final List<TabResponse> tabs;

    public SettingResponse(Homepee homepee, Member member, List<Tab> tabs) {
        this.profile = new SettingProfileResponse(member.getProfile());
        this.homepee = new SettingHomepeeResponse(homepee);
        if (tabs.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP7004);
        }
        this.tabs = tabs.stream()
                .map(TabResponse::new)
                .collect(Collectors.toList());
    }
}
