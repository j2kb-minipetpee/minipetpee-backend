package com.j2kb.minipetpee.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "name")
    private String name;

    private LocalDate birthday;

    private String species;

    private String personality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;
    private String gateImageUrl;

    private boolean delete;

    @OneToOne(mappedBy = "member")
    private Homepee homepee;
}

