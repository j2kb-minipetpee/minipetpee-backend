package com.j2kb.minipetpee.domain;

import lombok.Getter;
import javax.persistence.*;
import com.sun.istack.NotNull;

@Getter
@Entity
public class FanComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fan_comment_id")
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homepee_id")
    private Homepee homepee;

    @NotNull
    private String content;
}
