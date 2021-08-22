package com.j2kb.minipetpee.api.homepee.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.member.domain.Gender;
import com.j2kb.minipetpee.api.member.domain.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfileResponse {
    private final String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime birthday;
    private final String species;
    private final String personality;
    private final Gender gender;
    private final String profileImageUrl;

    public ProfileResponse(Profile profile) {
        this.name = profile.getName();
        this.birthday = profile.getBirthday();
        this.species = profile.getSpecies();
        this.personality = profile.getPersonality();
        this.gender = profile.getGender();
        this.profileImageUrl = profile.getProfileImageUrl();
    }
}
