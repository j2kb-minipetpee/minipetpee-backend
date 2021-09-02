package com.j2kb.minipetpee.api.member.domain;

import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateProfileRequest;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Profile {

    @Column(nullable = false)
    private String name;

    private LocalDateTime birthday;

    private String species;

    private String personality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    public void updateProfile(UpdateProfileRequest profileRequest) {
        this.name = profileRequest.getName();
        this.birthday = profileRequest.getBirthday();
        this.species = profileRequest.getSpecies();
        this.personality = profileRequest.getPersonality();
        this.gender = profileRequest.getGender();
        this.profileImageUrl = profileRequest.getProfileImageUrl();
    }
}
