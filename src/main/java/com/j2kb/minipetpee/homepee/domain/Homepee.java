package com.j2kb.minipetpee.homepee.domain;

import lombok.Getter;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.tab.domain.Tab;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Homepee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(name = "visit_count", nullable = false)
    @ColumnDefault("0")
    private int visitCount;

    @OneToMany(mappedBy = "homepee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tab> tabs = new ArrayList<>();
}
