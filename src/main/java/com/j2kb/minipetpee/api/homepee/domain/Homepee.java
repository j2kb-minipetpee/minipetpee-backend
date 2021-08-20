package com.j2kb.minipetpee.api.homepee.domain;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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

    @Builder.Default
    @OneToMany(mappedBy = "homepee", cascade = CascadeType.ALL)
    private List<Tab> tabList = new ArrayList<>();

    public void setTab(Tab tab) {
        this.getTabList().add(tab);
        tab.setHomepee(this);
    }
}
