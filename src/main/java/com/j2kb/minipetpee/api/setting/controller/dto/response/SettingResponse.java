package com.j2kb.minipetpee.api.setting.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SettingResponse {
    private final ProfileResponse profile;
    private final List<TabResponse> tabs;
}
