package com.j2kb.minipetpee.api.setting.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SettingResponse {
    private final SettingProfileResponse profile;
    private final List<SettingTabResponse> tabs;
}
