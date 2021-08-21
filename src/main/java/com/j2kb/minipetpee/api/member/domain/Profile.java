package com.j2kb.minipetpee.api.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Embeddable
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
}
