package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindSettingsProfileResponse {
    private final String email;
    private final String name;
    private final String birthday;
    private final String species;
    private final String personality;
    private final Gender gender;   //enum은 참조해도 되겠지?
    private final String profileImageUrl;
    private final String gateImageUrl;
}
