package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

    private String name;
    private String birthday;
    private String species;
    private String personality;
    private String gender;
    private String profileImageUrl;
    private String gateImageUrl;
}
