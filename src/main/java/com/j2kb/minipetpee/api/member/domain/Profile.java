package com.j2kb.minipetpee.api.member.domain;

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

    public void update(String name, LocalDateTime birthday, String species, String personality, Gender gender, String profileImageUrl) {
        this.name = name;
        this.birthday = birthday;
        this.species = species;
        this.personality = personality;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
    }
}
