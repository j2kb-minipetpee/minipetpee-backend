package com.j2kb.minipetpee.api.star.domain;

import com.j2kb.minipetpee.api.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_member_id", nullable = false)
    private Member starMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fan_member_id", nullable = false)
    private Member fanMember;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Star(Member starMember, Member fanMember) {
        this.starMember = starMember;
        this.fanMember = fanMember;
    }

    public String fanMemberName() {
        return fanMember.get
    }
}
