package com.j2kb.minipetpee.domain;

import lombok.Getter;
import javax.persistence.*;
import com.sun.istack.NotNull;

@Getter
@Entity
public class Homepee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homepee_id")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member; // ??

    @NotNull
    private String title;

    @NotNull
    private int visitCount;
}
