package com.j2kb.minipetpee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    @Column(name = "email", unique = true)
    @NonNull
    @Size(min = 10, max = 30)
    @Email
    private String email;

    @NonNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NonNull
    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "name")
    private String name;

    private LocalDate birthday;

    @Size(max = 20)
    private String species;

    @Size(max = 30)
    private String personality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;
    private String gateImageUrl;

    private boolean delete;

    @OneToOne(mappedBy = "member")
    private Homepee homepee;
}

