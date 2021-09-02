package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UpdateSettingRequest {
    @NotNull
    @Valid
    private final UpdateProfileRequest profile;

    @NotNull
    @Valid
    private final UpdateHomepeeRequest homepee;
}
