package com.j2kb.minipetpee.homepee.domain;

import com.j2kb.minipetpee.BaseTimeEntity;
import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
public class FanComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homepee_id", nullable = false)
    private Homepee homepee;

    @Column(nullable = false)
    private String content;
}
