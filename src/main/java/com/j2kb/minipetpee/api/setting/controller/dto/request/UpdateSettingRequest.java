package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateSettingRequest {
    private final UpdateProfileRequest profile;
    private final UpdateHomepeeRequest homepee;
}
