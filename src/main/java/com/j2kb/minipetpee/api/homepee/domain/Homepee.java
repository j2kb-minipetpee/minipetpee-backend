package com.j2kb.minipetpee.api.homepee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateHomepeeRequest;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Homepee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(name = "gate_image_url")
    private String gateImageUrl;

    @Column(name = "visit_count", nullable = false)
    @ColumnDefault("0")
    private int visitCount;

    @OneToMany(mappedBy = "homepee", cascade = CascadeType.ALL)
    private List<Tab> tabs = new ArrayList<>();

    public Homepee(Member member, List<Tab> tabs) {
        for (Tab tab : tabs) {
            setTabs(tab);
        }
        this.member = member;
        this.title = defaultTitle(member.name());
    }

    public Profile memberProfile() {
        if (Objects.isNull(member)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return member.getProfile();
    }

    public void setTabs(Tab tab) {
        this.getTabs().add(tab);
        tab.setHomepee(this);
    }

    private String defaultTitle(String name) {
        return name + "님의 미니홈피";
    }

    public void increaseVisitCount() {
        this.visitCount = this.visitCount+ 1;
    }
  
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateGateImageUrl(String gateImageUrl){
        this.gateImageUrl = gateImageUrl;
    }
}
