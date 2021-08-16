package com.j2kb.minipetpee.api.setting.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindSettingsResponse {
    private final FindSettingsProfileResponse profile;
    private final List<FindSettingsTabResponse> tabs;
}
