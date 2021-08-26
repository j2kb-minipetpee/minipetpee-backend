package com.j2kb.minipetpee.api.member.domain;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateProfileRequest;
import com.j2kb.minipetpee.global.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    @Column(nullable = false)
    private Profile profile;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Homepee homepee;

    public void updateProfile(UpdateProfileRequest profileRequest) {
        this.profile.setName(profileRequest.getName());
        this.profile.setBirthday(LocalDateTime.parse(profileRequest.getBirthday()));
        this.profile.setSpecies(profileRequest.getSpecies());
        this.profile.setPersonality(profileRequest.getPersonality());
        this.profile.setGender(Gender.valueOf(profileRequest.getGender()));
        this.profile.setProfileImageUrl(profileRequest.getProfileImageUrl());
    }
}

