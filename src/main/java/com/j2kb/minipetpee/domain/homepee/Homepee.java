package com.j2kb.minipetpee.domain.homepee;

import com.j2kb.minipetpee.domain.member.Member;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Homepee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homepee_id")
    private int id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member; // ??

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String title;

    @NonNull
    @PositiveOrZero()
    private int visitCount;
}
