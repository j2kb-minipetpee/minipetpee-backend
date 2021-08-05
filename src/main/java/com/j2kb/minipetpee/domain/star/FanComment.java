package com.j2kb.minipetpee.domain.star;

import com.j2kb.minipetpee.domain.BaseTimeEntity;
import com.j2kb.minipetpee.domain.homepee.Homepee;
import com.j2kb.minipetpee.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class FanComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fan_comment_id")
    private int id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homepee_id")
    private Homepee homepee;

    @NonNull
    @Size(max = 200)
    private String content;
}
