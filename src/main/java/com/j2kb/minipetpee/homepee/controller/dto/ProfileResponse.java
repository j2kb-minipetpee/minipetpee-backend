package com.j2kb.minipetpee.homepee.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.member.domain.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProfileResponse {
    private final String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime birthday;

    private final String species;
    private final String personality;
    private final Gender gender;
    private final String profileImageUrl;
}
