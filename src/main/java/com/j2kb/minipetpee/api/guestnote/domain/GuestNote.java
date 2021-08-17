package com.j2kb.minipetpee.api.guestnote.domain;

import com.j2kb.minipetpee.global.domain.BaseTimeEntity;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity
public class GuestNote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("1")
    private boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    private Tab tab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
