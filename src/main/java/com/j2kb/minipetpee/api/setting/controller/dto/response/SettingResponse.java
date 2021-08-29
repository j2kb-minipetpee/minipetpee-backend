package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class SettingResponse {
    private final ProfileResponse profile;
    private final List<TabResponse> tabs;

    public SettingResponse(Homepee homepee, Member member, List<Tab> tabs) {
        this.profile = new ProfileResponse(member.getProfile(), homepee);
        this.tabs = tabs.stream()
                .map(TabResponse::new)
                .collect(Collectors.toList());
    }
}
