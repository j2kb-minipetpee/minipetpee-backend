package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Gender;
import com.j2kb.minipetpee.api.member.domain.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfileResponse {
    private final String email;
    private final String name;
    private final LocalDateTime birthday;
    private final String species;
    private final String personality;
    private final Gender gender;
    private final String profileImageUrl;
    private final String gateImageUrl;

    public ProfileResponse(Profile profile, Homepee homepee) {
        this.email = profile.getProfileImageUrl();
        this.name = profile.getName();
        this.birthday = profile.getBirthday();
        this.species = profile.getSpecies();
        this.personality = profile.getPersonality();
        this.gender = profile.getGender();
        this.profileImageUrl = profile.getProfileImageUrl();
        this.gateImageUrl = homepee.getGateImageUrl();
    }
}
