package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.member.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateSettingRequest {
    @Valid
    @NotNull(message = "EMP7017")
    private UpdateProfileRequest profile;

    @Valid
    @NotNull(message = "EMP7018")
    private UpdateHomepeeRequest homepee;

    public Profile toProfile() {
        return profile.toProfile();
    }
}
