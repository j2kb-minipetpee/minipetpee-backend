package com.j2kb.minipetpee.member.domain;

import com.j2kb.minipetpee.BaseTimeEntity;
import com.j2kb.minipetpee.homepee.domain.Homepee;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private LocalDateTime birthday;
    private String species;
    private String personality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "gate_image_url")
    private String gateImageUrl;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(mappedBy = "member")
    private Homepee homepee;
}

