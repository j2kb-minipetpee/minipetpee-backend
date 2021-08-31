package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UpdateSettingRequest {
    @NotNull
    private final UpdateProfileRequest profile;

    @NotNull
    private final UpdateHomepeeRequest homepee;
}
