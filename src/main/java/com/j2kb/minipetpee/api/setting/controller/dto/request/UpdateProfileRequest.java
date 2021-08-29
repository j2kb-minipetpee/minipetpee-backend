package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.member.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

    private String name;
    private LocalDateTime birthday;
    private String species;
    private String personality;
    private Gender gender;
    private String profileImageUrl;
}
